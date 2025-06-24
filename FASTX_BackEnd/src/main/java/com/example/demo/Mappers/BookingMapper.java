package com.example.demo.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.PassengerDTO;
import com.example.demo.DTO.SeatDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Bus;
import com.example.demo.Entity.Passenger;
import com.example.demo.Entity.Route;
import com.example.demo.Entity.Seat;
import com.example.demo.Entity.User;

public class BookingMapper {
	
	 public static BookingDTO toDto(Booking booking) {
	        if (booking == null) return null;

	        BookingDTO dto = new BookingDTO();
	        dto.setBookingId(booking.getBookingId());
	        dto.setBookingDate(booking.getBookingDate());
	        dto.setTotalAmount(booking.getTotalAmount());
	        dto.setBookingStatus(booking.getBookingStatus());

	        dto.setUser(UserMapper.toDTO(booking.getUser()));
	        dto.setRoute(RouteMapper.toDTO(booking.getRoute()));
	        dto.setBus(BusMapper.toDto(booking.getBus()));
	        dto.setPayment(PaymentMapper.toDto(booking.getPayment()));

	        List<PassengerDTO> passengerDtos = booking.getPassengers()
	                .stream()
	                .map(passenger -> PassengerMapper.toDto(passenger))
	                .collect(Collectors.toList());
	        dto.setPassengers(passengerDtos);

	        List<SeatDTO> seatDtos = booking.getSeats()
	                .stream()
	                .map(seat -> SeatMapper.toDto(seat))
	                .collect(Collectors.toList());
	        dto.setSeats(seatDtos);

	        return dto;
	    }

	    public static Booking toEntity(BookingDTO dto) {
	        if (dto == null) return null;

	        Booking booking = new Booking();
	        booking.setBookingId(dto.getBookingId());
	        booking.setBookingDate(dto.getBookingDate());
	        booking.setTotalAmount(dto.getTotalAmount());
	        booking.setBookingStatus(dto.getBookingStatus());

	        booking.setUser(UserMapper.toEntity(dto.getUser()));
	        booking.setRoute(RouteMapper.toEntity(dto.getRoute()));
	        booking.setBus(BusMapper.toEntity(dto.getBus()));
	        booking.setPayment(PaymentMapper.toEntity(dto.getPayment()));

	        List<Passenger> passengers = dto.getPassengers()
	                .stream()
	                .map(passengerDto -> PassengerMapper.toEntity(passengerDto))
	                .collect(Collectors.toList());
	        booking.setPassengers(passengers);

	        List<Seat> seats = dto.getSeats()
	                .stream()
	                .map(seatDto -> SeatMapper.toEntity(seatDto))
	                .collect(Collectors.toList());
	        booking.setSeats(seats);

	        return booking;
	    }

}
