package com.platform.agriwatch.domain.repository.dataSensor.lastData;

import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LastAirDataRepository extends JpaRepository<LastAirData, Long> {
    Optional<LastAirData> findBySensor(Sensor sensor);
}