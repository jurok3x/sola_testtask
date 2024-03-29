package com.yukotsiuba.powerstation.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "power_stations")
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
    private Boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "powerStation")
    private List<@Valid Connector> connectors;
}
