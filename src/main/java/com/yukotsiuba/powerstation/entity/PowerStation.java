package com.yukotsiuba.powerstation.entity;

import com.yukotsiuba.powerstation.entity.annotations.PowerStationValidator;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "power_stations")
@PowerStationValidator(conditionallyMandatoryFields = {"title", "description", "address", "coordinates"})
public class PowerStation {
    @Id
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "is_public")
    @NotNull(message = "isPublic can not be null.")
    private Boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "powerStation")
    @Size(min = 1, max = 8, message = "The connectors list must have between 1 and 8 entries")
    @Valid
    private List<Connector> connectors;
}
