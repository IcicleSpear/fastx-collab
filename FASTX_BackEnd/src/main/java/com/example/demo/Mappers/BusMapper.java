package com.example.demo.Mappers;

import com.example.demo.DTO.BusDTO;
import com.example.demo.DTO.RouteDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Bus;
import com.example.demo.Entity.Route;
import com.example.demo.Entity.User;

public class BusMapper {

	public static BusDTO toDto(Bus bus) {
        if (bus == null) return null;

        BusDTO dto = new BusDTO();
        dto.setBusId(bus.getBusId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusName(bus.getBusName());
        dto.setBusType(bus.getBusType());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAmenities(bus.getAmenities());
        dto.setBusStatus(bus.getBusStatus());
        dto.setDate(bus.getDate()); 

        // For nested operator
        if (bus.getOperator() != null) {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(bus.getOperator().getUserId());
            userDto.setUserName(bus.getOperator().getUserName());
            userDto.setEmail(bus.getOperator().getEmail());
            userDto.setPhone(bus.getOperator().getPhone());
            userDto.setAddress(bus.getOperator().getAddress());
            userDto.setPassword(bus.getOperator().getPassword());
            userDto.setRole(bus.getOperator().getRole());
            dto.setOperator(userDto);
        }

        // Route should be set separately, possibly from RouteMapper or service layer
        dto.setRoute(null); // optional

        return dto;
    }

    public static Bus toEntity(BusDTO busDto) {
        if (busDto == null) return null;

        Bus bus = new Bus();
        bus.setBusId(busDto.getBusId());
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusName(busDto.getBusName());
        bus.setBusType(busDto.getBusType());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAmenities(busDto.getAmenities());
        bus.setBusStatus(busDto.getBusStatus());
        bus.setDate(busDto.getDate());

        if (busDto.getOperator() != null) {
            User operator = new User();
            operator.setUserId(busDto.getOperator().getUserId());
            bus.setOperator(operator);
        }
        if (busDto.getRoute() != null) {
            Route route = new Route();
            route.setRouteId(busDto.getRoute().getRouteId());
            bus.setRoute(route);
        }

        // Bookings, Seats, and Route are managed elsewhere
        return bus;
    }                                                                                       	
	
}
