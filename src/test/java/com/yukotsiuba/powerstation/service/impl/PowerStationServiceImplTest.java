package com.yukotsiuba.powerstation.service.impl;

import com.yukotsiuba.powerstation.dto.ConnectorRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationResponseDTO;
import com.yukotsiuba.powerstation.entity.Connector;
import com.yukotsiuba.powerstation.entity.PowerStation;
import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import com.yukotsiuba.powerstation.repository.PowerStationRepository;
import com.yukotsiuba.powerstation.service.PowerStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PowerStationServiceImplTest {

    @Mock
    private PowerStationRepository repository;

    private PowerStationService service;

    @BeforeEach
    void setUp() {
        repository = mock(PowerStationRepository.class);
        service = new PowerStationServiceImpl(repository);
    }

    @Test
    void whenPowerStationIsProvided_thenReturnCorrectPowerStation() {
        PowerStation station = prepareStation();
        given(repository.save(any(PowerStation.class))).willReturn(station);

        PowerStationRequestDTO request = prepareRequest();
        PowerStationResponseDTO response = service.save(request);

        assertNotNull(response.getId());
        assertNotNull(response.getConnectors().get(0).getId());

        verify(repository).save(any(PowerStation.class));
    }

    private PowerStation prepareStation() {
        PowerStation station = new PowerStation();
        station.setId(UUID.randomUUID());
        station.setIsPublic(false);
        Connector connector = new Connector();
        connector.setId(UUID.randomUUID());
        connector.setMaxPower(200);
        connector.setType(ConnectorType.Type1);
        station.setConnectors(
                Arrays.asList(connector)
        );
        return station;
    }

    private PowerStationRequestDTO prepareRequest() {
        return PowerStationRequestDTO.builder()
                .isPublic(false)
                .connectors(
                        Arrays.asList(new ConnectorRequestDTO(ConnectorType.Type1.name(), 200))
                )
                .build();
    }

}