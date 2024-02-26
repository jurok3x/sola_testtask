package com.yukotsiuba.powerstation.service;

import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.dto.PowerStationResponseDTO;

public interface PowerStationService {
    PowerStationResponseDTO save(PowerStationRequestDTO request);
}
