package com.example.demo.Mappers;

import com.example.demo.DTO.PassengerDTO;
import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Passenger;

public class PassengerMapper {
    public static PassengerDTO toDto(Passenger passenger) {
        if (passenger == null) return null;

        PassengerDTO dto = new PassengerDTO();
        dto.setPassengerId(passenger.getPassengerId());
        dto.setName(passenger.getPassengerName());
        dto.setAge(passenger.getPassengerAge());
        dto.setGender(passenger.getGender());
        dto.setSeatNumber(passenger.getSeatNumber());
       
        if (passenger.getBooking() != null) {
            dto.setBookingId(passenger.getBooking().getBookingId());
        }

        return dto;
    }

    public static Passenger toEntity(PassengerDTO dto) {
        if (dto == null) return null;

        Passenger passenger = new Passenger();
        passenger.setPassengerId(dto.getPassengerId());
        passenger.setPassengerName(dto.getName());
        passenger.setPassengerAge(dto.getAge());
        passenger.setGender(dto.getGender());
        passenger.setSeatNumber(dto.getSeatNumber());

        // Set Booking only by ID to avoid deep loading
        Booking booking = new Booking();
        booking.setBookingId(dto.getBookingId());
        passenger.setBooking(booking);

        return passenger;
    }

}
