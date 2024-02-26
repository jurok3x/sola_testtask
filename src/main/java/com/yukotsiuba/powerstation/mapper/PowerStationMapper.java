package com.yukotsiuba.powerstation.mapper;

import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationResponseDTO;
import com.yukotsiuba.powerstation.entity.PowerStation;

public class PowerStationMapper {
    public static PowerStation toEntity(PowerStationRequestDTO request) {
        PowerStation powerStation = new PowerStation();
        powerStation.setTitle(request.getTitle());
        powerStation.setDescription(request.getDescription());
        powerStation.setAddress(request.getAddress());
        powerStation.setCoordinates(request.getCoordinates());
        powerStation.setIsPublic(request.getIsPublic());
        powerStation.setConnectors(request.getConnectors().stream().map(ConnectorMapper::toEntity).toList());
        return powerStation;
    }

    public static PowerStationResponseDTO toResponseDTO (PowerStation station) {
        return PowerStationResponseDTO
                .builder()
                .id(station.getId())
                .title(station.getTitle())
                .description(station.getDescription())
                .address(station.getAddress())
                .coordinates(station.getCoordinates())
                .isPublic(station.getIsPublic())
                .connectors(station.getConnectors().stream().map(ConnectorMapper::toResponseDTO).toList())
                .build();
    }
}
