package com.hexaware.fastx.mapper;

import com.hexaware.fastx.dto.BusDTO;
import com.hexaware.fastx.entity.Bus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BusDTO toDTO(Bus bus) {
        return modelMapper.map(bus, BusDTO.class);
    }

    public Bus toEntity(BusDTO busDTO) {
        return modelMapper.map(busDTO, Bus.class);
    }
}
