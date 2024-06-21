package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.repository.SensorRepository;
import com.platform.agriwatch.domain.service.SensorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService{

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Optional<Sensor> getBySensorName(String name) {
        return sensorRepository.findBySensorName(name);
    }
}
