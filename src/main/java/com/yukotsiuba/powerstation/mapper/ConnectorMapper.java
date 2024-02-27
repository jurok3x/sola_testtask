package com.yukotsiuba.powerstation.mapper;

import com.yukotsiuba.powerstation.dto.ConnectorRequestDTO;
import com.yukotsiuba.powerstation.dto.ConnectorResponseDTO;
import com.yukotsiuba.powerstation.entity.Connector;
import com.yukotsiuba.powerstation.entity.enums.ConnectorType;

public class ConnectorMapper {
    public static Connector toEntity(ConnectorRequestDTO request) {
        Connector connector = new Connector();
        connector.setType(ConnectorType.valueOf(request.getType()));
        connector.setMaxPower(request.getMaxPower());
        return connector;
    }

    public static ConnectorResponseDTO toResponseDTO(Connector connector) {
        return ConnectorResponseDTO
                .builder()
                .id(connector.getId())
                .type(connector.getType())
                .maxPower(connector.getMaxPower())
                .build();
    }
}
