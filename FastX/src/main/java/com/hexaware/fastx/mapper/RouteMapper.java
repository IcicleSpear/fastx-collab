package com.hexaware.fastx.mapper;

import com.hexaware.fastx.dto.RouteDTO;
import com.hexaware.fastx.entity.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RouteDTO toDTO(Route route) {
        return modelMapper.map(route, RouteDTO.class);
    }

    public Route toEntity(RouteDTO routeDTO) {
        return modelMapper.map(routeDTO, Route.class);
    }
}
