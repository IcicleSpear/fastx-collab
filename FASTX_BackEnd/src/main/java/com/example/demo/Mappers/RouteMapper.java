package com.example.demo.Mappers;

import com.example.demo.DTO.RouteDTO;
import com.example.demo.Entity.Route;

public class RouteMapper {

	public static RouteDTO toDTO(Route route) {
        if (route == null) return null;

        return new RouteDTO(
            route.getRouteId(),
            route.getOrigin(),
            route.getDestination(),
            route.getDepartureTime(),
            route.getArrivalTime(),
            route.getDistance(),
            route.getFare()
        );
    }

    public static Route toEntity(RouteDTO dto) {
        if (dto == null) return null;

        Route route = new Route();
        route.setRouteId(dto.getRouteId());
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setDistance(dto.getDistance());
        route.setFare(dto.getFare());

        return route;
    }
	
	
}
