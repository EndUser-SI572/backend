package com.platform.agriwatch.domain.repository.dataSensor;

import com.platform.agriwatch.domain.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Optional<Sensor> findBySensorName (String sensorName);
    List<Sensor> findBySensorType(String sensorType);
}
