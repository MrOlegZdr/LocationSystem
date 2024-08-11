package com.home.project.locationsystem.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.home.project.locationsystem.dto.UserDTO;
import com.home.project.locationsystem.entity.User;
import com.home.project.locationsystem.mapper.UserMapper;
import com.home.project.locationsystem.service.UserService;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
    public Mono<ServerResponse> registerUser(ServerRequest request) {
        return request.bodyToMono(UserDTO.class)
                .flatMap(userDTO -> {
                    User user = userService.registerUser(userDTO.getName(), userDTO.getEmail());
                    UserDTO responseDTO = userMapper.toDTO(user);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(responseDTO);
                });
    }

    public Mono<ServerResponse> getUserLocations(ServerRequest request) {
        Long userId = Long.valueOf(request.pathVariable("userId"));
        return Mono.justOrEmpty(userService.findUserById(userId))
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userMapper.toDTO(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
