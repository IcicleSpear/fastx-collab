package com.hexaware.fastx.mapper;

import com.hexaware.fastx.dto.CancellationDTO;
import com.hexaware.fastx.entity.Cancellation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancellationMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CancellationDTO toDTO(Cancellation cancellation) {
        return modelMapper.map(cancellation, CancellationDTO.class);
    }

    public Cancellation toEntity(CancellationDTO cancellationDTO) {
        return modelMapper.map(cancellationDTO, Cancellation.class);
    }
}
