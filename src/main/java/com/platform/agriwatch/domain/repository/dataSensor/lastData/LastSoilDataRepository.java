package com.platform.agriwatch.domain.repository.dataSensor.lastData;

import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LastSoilDataRepository extends JpaRepository<LastSoilData, Long> {
    Optional<LastSoilData> findBySensor(Sensor sensor);
}
