package com.yukotsiuba.powerstation.dto;

import com.yukotsiuba.powerstation.entity.annotations.ValidEnum;
import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectorRequestDTO {

    @NotNull(message = "Type can not be null.")
    @ValidEnum(enumClass = ConnectorType.class)
    private String type;

    @NotNull(message = "Maximum power can not be null.")
    @Positive(message = "Invalid maximum power value.")
    private Integer maxPower;
}
