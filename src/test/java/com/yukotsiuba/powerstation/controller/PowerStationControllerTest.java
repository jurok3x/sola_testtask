package com.yukotsiuba.powerstation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukotsiuba.powerstation.configuration.TestDBConfig;
import com.yukotsiuba.powerstation.dto.ConnectorRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.entity.enums.ConnectorType;
import com.yukotsiuba.powerstation.service.PowerStationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import({TestDBConfig.class})
@AutoConfigureMockMvc
class PowerStationControllerTest {

    private static final String TITLE_FIELD_ERROR = "Title field is mandatory for public stations.";
    private static final String DESCRIPTION_FIELD_IS_MANDATORY_ERROR = "Description field is mandatory for public stations.";
    private static final String ADDRESS_FIELD_ERROR = "Address field is mandatory for public stations.";
    private static final String COORDINATES_FIELD_ERROR = "Coordinates field is mandatory for public stations.";
    private static final String IS_PUBLIC_ERROR = "Please specify station's publicity.";
    private static final String CONNECTORS_FIELD_ERROR = "Connectors field is mandatory.";
    private static final String INVALID_MAXIMUM_POWER_ERROR = "Invalid maximum power value.";
    private static final String INVALID_ENUM_VALUE = "Invalid enum value.";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PowerStationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(service);
    }

    @Test
    void createPowerStation_ShouldReturnCreated() throws Exception {
        PowerStationRequestDTO request = prepareRequest();

        mockMvc.perform(post("/api/power-stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(service).save(any(PowerStationRequestDTO.class));
    }

    @Test
    void createInvalidPublicPowerStation_ShouldErrorBadRequest() throws Exception {
        PowerStationRequestDTO request = prepareRequest();
        request.setTitle(null);
        request.setDescription(null);
        request.setAddress(null);
        request.setCoordinates(null);

        mockMvc.perform(post("/api/power-stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(TITLE_FIELD_ERROR)))
                .andExpect(content().string(containsString(DESCRIPTION_FIELD_IS_MANDATORY_ERROR)))
                .andExpect(content().string(containsString(ADDRESS_FIELD_ERROR)))
                .andExpect(content().string(containsString(COORDINATES_FIELD_ERROR)));

        verifyNoInteractions(service);
    }

    @Test
    void createPrivatePowerStation_ShouldReturnCreated() throws Exception {
        PowerStationRequestDTO request = prepareRequest();
        request.setIsPublic(false);
        request.setTitle(null);
        request.setDescription(null);
        request.setAddress(null);
        request.setCoordinates(null);

        mockMvc.perform(post("/api/power-stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(service).save(any(PowerStationRequestDTO.class));
    }

    @Test
    void createInvalidPowerStation_ShouldErrorBadRequest() throws Exception {
        PowerStationRequestDTO request = prepareRequest();
        request.setConnectors(null);
        request.setIsPublic(null);

        mockMvc.perform(post("/api/power-stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(IS_PUBLIC_ERROR)))
                .andExpect(content().string(containsString(CONNECTORS_FIELD_ERROR)));

        verifyNoInteractions(service);
    }

    @Test
    void createPowerStationWithInvalidConnectorsAndTitle_ShouldErrorBadRequest() throws Exception {
        PowerStationRequestDTO request = prepareRequest();
        request.setTitle(null);
        request.getConnectors().get(0).setMaxPower(0);
        request.getConnectors().get(0).setType("error");

        mockMvc.perform(post("/api/power-stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(TITLE_FIELD_ERROR)))
                .andExpect(content().string(containsString(INVALID_MAXIMUM_POWER_ERROR)))
                .andExpect(content().string(containsString(INVALID_ENUM_VALUE)));

        verifyNoInteractions(service);
    }

    private PowerStationRequestDTO prepareRequest() {
        return PowerStationRequestDTO.builder()
                .title("Station #1")
                .description("description")
                .address("Main str. 2")
                .coordinates("0000")
                .isPublic(true)
                .connectors(
                        Arrays.asList(new ConnectorRequestDTO(ConnectorType.Type1.name(), 200))
                )
                .build();
    }
}
