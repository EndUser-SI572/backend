package com.platform.agriwatch.domain.repository.dataSensor;

import com.platform.agriwatch.domain.model.dataSensor.SoilData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoilDataRepository extends JpaRepository<SoilData, Long> {
}
