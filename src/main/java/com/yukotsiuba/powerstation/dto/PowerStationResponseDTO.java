package com.yukotsiuba.powerstation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PowerStationResponseDTO {

    private UUID id;

    private String title;

    private String description;

    private String address;

    private String coordinates;

    private Boolean isPublic;

    private List<ConnectorResponseDTO> connectors;
}
