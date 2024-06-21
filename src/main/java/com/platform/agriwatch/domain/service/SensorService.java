package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.domain.model.Sensor;

import java.util.Optional;

public interface SensorService {

    Sensor createSensor(Sensor sensor);

    Optional<Sensor> getBySensorName(String name);
}
