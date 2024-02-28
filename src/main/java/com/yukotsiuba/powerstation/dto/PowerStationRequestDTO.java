package com.yukotsiuba.powerstation.dto;

import com.yukotsiuba.powerstation.entity.annotations.PowerStationValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@PowerStationValidator(conditionallyMandatoryFields = {"title", "description", "address", "coordinates"})
@Builder
public class PowerStationRequestDTO {
    private String title;

    private String description;

    private String address;

    private String coordinates;

    @NotNull(message = "Please specify station's publicity.")
    private Boolean isPublic;

    @Size(min = 1, max = 8, message = "The connectors list must have between 1 and 8 entries.")
    @NotNull(message = "Connectors field is mandatory.")
    @Valid
    private List<@Valid ConnectorRequestDTO> connectors;
}
