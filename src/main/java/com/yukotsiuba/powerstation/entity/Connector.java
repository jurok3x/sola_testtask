package com.yukotsiuba.powerstation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "connectors")
public class Connector {
    @Id
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "power_station_id")
    private PowerStation powerStation;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type can not be null.")
    private ConnectorType type;

    @Column(name = "max_power", columnDefinition = "INTEGER CHECK (max_power > 0)")
    @NotNull
    @Positive(message = "Invalid maximum power value.")
    private Integer maxPower;
}
