package com.yukotsiuba.powerstation.service.impl;

import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationResponseDTO;
import com.yukotsiuba.powerstation.entity.PowerStation;
import com.yukotsiuba.powerstation.mapper.PowerStationMapper;
import com.yukotsiuba.powerstation.repository.PowerStationRepository;
import com.yukotsiuba.powerstation.service.PowerStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStationServiceImpl implements PowerStationService {

    private final PowerStationRepository repository;

    @Override
    public PowerStationResponseDTO save(PowerStationRequestDTO request) {
        PowerStation powerStation = PowerStationMapper.toEntity(request);
        powerStation.setId(UUID.randomUUID());
        powerStation.getConnectors().stream().forEach(
                connector -> connector.setId(UUID.randomUUID())
        );
        PowerStation savedStation = repository.save(powerStation);
        PowerStationResponseDTO response = PowerStationMapper.toResponseDTO(savedStation);
        return response;
    }
}
