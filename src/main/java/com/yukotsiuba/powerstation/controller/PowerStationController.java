package com.yukotsiuba.powerstation.controller;

import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationResponseDTO;
import com.yukotsiuba.powerstation.service.PowerStationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/power-stations")
@RequiredArgsConstructor
public class PowerStationController {

    private final PowerStationService service;

    @PostMapping
    public ResponseEntity<PowerStationResponseDTO> createPowerStation(
            @Valid @RequestBody PowerStationRequestDTO request) {
        PowerStationResponseDTO response = service.save(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
