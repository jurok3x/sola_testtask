package com.yukotsiuba.powerstation.dto;

import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ConnectorRequestDTO {
    @NotNull(message = "Type can not be null.")
    private ConnectorType type;

    @NotNull
    @Positive(message = "Invalid maximum power value.")
    private Integer maxPower;
}
