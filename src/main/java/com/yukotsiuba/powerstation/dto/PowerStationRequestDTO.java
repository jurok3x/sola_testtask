package com.yukotsiuba.powerstation.dto;

import com.yukotsiuba.powerstation.entity.annotations.PowerStationValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@PowerStationValidator
public class PowerStationRequestDTO {
    private String title;

    private String description;

    private String address;

    private String coordinates;

    @NotNull(message = "isPublic can not be null.")
    private Boolean isPublic;

    @Size(min = 1, max = 8, message = "The connectors list must have between 1 and 8 entries")
    @Valid
    private List<ConnectorRequestDTO> connectors;
}
