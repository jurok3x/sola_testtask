package com.yukotsiuba.powerstation.repository;

import com.yukotsiuba.powerstation.entity.PowerStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PowerStationRepository extends JpaRepository<PowerStation, UUID> {
}
