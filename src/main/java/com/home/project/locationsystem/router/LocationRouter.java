package com.home.project.locationsystem.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.home.project.locationsystem.handler.LocationHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class LocationRouter {

	@Bean
	public RouterFunction<ServerResponse> locationRoutes(LocationHandler locationHandler) {
		return RouterFunctions.route(POST("/locations"), locationHandler::createLocation)
				.andRoute(POST("/locations/{locationId}/share"), locationHandler::shareLocation)
				.andRoute(GET("/locations/{locationId}/users"), locationHandler::getUsersOnLocation)
				.andRoute(PATCH("/locations/{locationId}/users/{userId}"), locationHandler::updateAccessLevel);
	}

}
