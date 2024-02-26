package com.yukotsiuba.powerstation.service.impl;

import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationResponseDTO;
import com.yukotsiuba.powerstation.entity.PowerStation;
import com.yukotsiuba.powerstation.mapper.PowerStationMapper;
import com.yukotsiuba.powerstation.repository.PowerStationRepository;
import com.yukotsiuba.powerstation.service.PowerStationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PowerStationServiceImpl implements PowerStationService {

    private final PowerStationRepository repository;

    @Override
    public PowerStationResponseDTO save(PowerStationRequestDTO request) {
        PowerStation powerStation = repository.save(PowerStationMapper.toEntity(request));
        PowerStationResponseDTO response = PowerStationMapper.toResponseDTO(powerStation);
        return response;
    }
}
