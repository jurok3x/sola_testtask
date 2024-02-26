package com.yukotsiuba.powerstation.dto;

import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ConnectorResponseDTO {

    private UUID id;

    private ConnectorType type;

    private Integer maxPower;
}
