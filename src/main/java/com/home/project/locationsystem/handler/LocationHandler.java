package com.home.project.locationsystem.handler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.home.project.locationsystem.dto.LocationAccessDTO;
import com.home.project.locationsystem.dto.LocationDTO;
import com.home.project.locationsystem.entity.AccessLevel;
import com.home.project.locationsystem.entity.Location;
import com.home.project.locationsystem.entity.LocationAccess;
import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.mapper.LocationAccessMapper;
import com.home.project.locationsystem.mapper.LocationMapper;
import com.home.project.locationsystem.service.LocationAccessService;
import com.home.project.locationsystem.service.LocationService;
import com.home.project.locationsystem.service.UserService;

import reactor.core.publisher.Mono;

@Component
public class LocationHandler {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationAccessService locationAccessService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocationMapper locationMapper;
	
	@Autowired
	private LocationAccessMapper locationAccessMapper;
	
    public Mono<ServerResponse> createLocation(ServerRequest request) {
        return request.bodyToMono(LocationDTO.class)
                .flatMap(locationDTO -> {
                	Optional<User> ownerOpt = userService.findUserByEmail(locationDTO.getOwnerEmail());
                	if (ownerOpt.isPresent()) {
                		User owner = ownerOpt.get();
                        Location location = locationService.createLocation(
                                owner.getId(),
                                locationDTO.getName(),
                                locationDTO.getAddress()
                        );
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(locationMapper.toDTO(location));
                	} else {
                		return ServerResponse.badRequest().bodyValue("Owner not found");
                	}
                });
    }

    public Mono<ServerResponse> shareLocation(ServerRequest request) {
        Long locationId = Long.valueOf(request.pathVariable("locationId"));
        return request.bodyToMono(LocationAccessDTO.class)
                .flatMap(locationAccessDTO -> {
                    LocationAccess locationAccess = locationAccessService.shareLocation(
                            locationId,
                            locationAccessDTO.getUserId(),
                            AccessLevel.valueOf(locationAccessDTO.getAccessLevel())
                    );
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(locationAccessMapper.toDTO(locationAccess));
                });
    }

    public Mono<ServerResponse> getUsersOnLocation(ServerRequest request) {
        Long locationId = Long.valueOf(request.pathVariable("locationId"));
        return Mono.justOrEmpty(locationService.findLocationById(locationId))
                .flatMap(location -> {
                    List<LocationAccessDTO> users = location.getSharedUsers().stream()
                            .map(locationAccessMapper::toDTO)
                            .collect(Collectors.toList());
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(users);
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateAccessLevel(ServerRequest request) {
        Long locationId = Long.valueOf(request.pathVariable("locationId"));
        Long userId = Long.valueOf(request.pathVariable("userId"));
        return request.bodyToMono(LocationAccessDTO.class)
                .flatMap(locationAccessDTO -> {
                    locationAccessService.updateAccessLevel(
                            locationId,
                            userId,
                            AccessLevel.valueOf(locationAccessDTO.getAccessLevel())
                    );
                    return ServerResponse.noContent().build();
                });
    }

}
