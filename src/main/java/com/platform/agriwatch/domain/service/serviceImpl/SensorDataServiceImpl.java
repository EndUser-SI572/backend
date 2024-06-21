package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.AirData;
import com.platform.agriwatch.domain.model.dataSensor.SoilData;
import com.platform.agriwatch.domain.repository.SensorRepository;
import com.platform.agriwatch.domain.repository.dataSensor.AirDataRepository;
import com.platform.agriwatch.domain.repository.dataSensor.SoilDataRepository;
import com.platform.agriwatch.domain.service.SensorDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorDataServiceImpl implements SensorDataService {

    private final AirDataRepository airDataRepository;
    private final SoilDataRepository soilDataRepository;
    private final SensorRepository sensorRepository;

    @Override
    public SoilData addSoilData(SoilDataRequest soilDataRequest) {

        Optional<Sensor> sensor = sensorRepository.findBySensorName(soilDataRequest.getSensorName());
        if (sensor.isPresent()) {
            SoilData soilData = new SoilData(soilDataRequest);
            soilData.setSensor(sensor.get());
            return soilDataRepository.save(soilData);
        }
        return null;
    }

    @Override
    public AirData addAmbientData(AirDataRequest airDataRequest) {
        Optional<Sensor> sensor = sensorRepository.findBySensorName(airDataRequest.getSensorName());
        if (sensor.isPresent()) {
            AirData airData = new AirData(airDataRequest);
            airData.setSensor(sensor.get());
            return airDataRepository.save(airData);
        }
        return null;
    }
}
