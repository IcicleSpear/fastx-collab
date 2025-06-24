package com.example.demo.Mappers;

import com.example.demo.DTO.SeatDTO;
import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Bus;
import com.example.demo.Entity.Seat;

public class SeatMapper {
	 public static SeatDTO toDto(Seat seat) {
	        if (seat == null) return null;

	        SeatDTO dto = new SeatDTO();
	        dto.setSeatId(seat.getSeatId());
	        dto.setSeatNumber(seat.getSeatNumber());
	        dto.setAvailable(seat.isAvailable());

	        if (seat.getBus() != null) {
	            dto.setBusId(seat.getBus().getBusId());
	        }

	        if (seat.getBooking() != null) {
	            dto.setBookingId(seat.getBooking().getBookingId());
	        }

	        return dto;
	    }

	    public static Seat toEntity(SeatDTO seatDto) {
	        if (seatDto == null) return null;

	        Seat seat = new Seat();
	        seat.setSeatId(seatDto.getSeatId());
	        seat.setSeatNumber(seatDto.getSeatNumber());
	        seat.setAvailable(seatDto.isAvailable());

	        Bus bus = new Bus();
	        bus.setBusId(seatDto.getBusId());
	        seat.setBus(bus);

	        if (seatDto.getBookingId() != null) {
	            Booking booking = new Booking();
	            booking.setBookingId(seatDto.getBookingId());
	            seat.setBooking(booking);
	        }

	        return seat;
	    }
}
