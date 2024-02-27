package com.yukotsiuba.powerstation.dto;

import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import lombok.Data;

@Data
public class ConnectorRequestDTO {
    private ConnectorType type;

    private Integer maxPower;
}
