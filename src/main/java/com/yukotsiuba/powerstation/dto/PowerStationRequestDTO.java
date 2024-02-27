package com.yukotsiuba.powerstation.dto;

import lombok.Data;

import java.util.List;

@Data
public class PowerStationRequestDTO {
    private String title;

    private String description;

    private String address;

    private String coordinates;

    private Boolean isPublic;

    private List<ConnectorRequestDTO> connectors;
}
