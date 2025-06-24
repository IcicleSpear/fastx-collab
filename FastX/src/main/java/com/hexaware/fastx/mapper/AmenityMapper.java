package com.hexaware.fastx.mapper;

import com.hexaware.fastx.dto.AmenityDTO;
import com.hexaware.fastx.entity.Amenity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AmenityDTO toDTO(Amenity amenity) {
        return modelMapper.map(amenity, AmenityDTO.class);
    }

    public Amenity toEntity(AmenityDTO amenityDTO) {
        return modelMapper.map(amenityDTO, Amenity.class);
    }
}
