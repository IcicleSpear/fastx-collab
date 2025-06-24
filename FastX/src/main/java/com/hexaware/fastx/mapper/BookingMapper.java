package com.hexaware.fastx.mapper;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.entity.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BookingDTO toDTO(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }

    public Booking toEntity(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO, Booking.class);
    }
}
