package com.yukotsiuba.powerstation.repository;

import com.yukotsiuba.powerstation.entity.PowerStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PowerStationRepository extends JpaRepository<PowerStation, UUID> {
}
