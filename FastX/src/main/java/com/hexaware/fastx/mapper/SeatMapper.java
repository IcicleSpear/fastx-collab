package com.hexaware.fastx.mapper;

import com.hexaware.fastx.dto.SeatDTO;
import com.hexaware.fastx.entity.Seat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SeatDTO toDTO(Seat seat) {
        return modelMapper.map(seat, SeatDTO.class);
    }

    public Seat toEntity(SeatDTO seatDTO) {
        return modelMapper.map(seatDTO, Seat.class);
    }
}
