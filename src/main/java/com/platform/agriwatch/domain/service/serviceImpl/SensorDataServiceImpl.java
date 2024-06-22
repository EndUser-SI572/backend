package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.application.controller.SensorWebSocketController;
import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.application.dto.response.sensor.LastAirDataResponse;
import com.platform.agriwatch.application.dto.response.sensor.LastSoilDataResponse;
import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.AirData;
import com.platform.agriwatch.domain.model.dataSensor.SoilData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;
import com.platform.agriwatch.domain.repository.SensorRepository;
import com.platform.agriwatch.domain.repository.dataSensor.AirDataRepository;
import com.platform.agriwatch.domain.repository.dataSensor.SoilDataRepository;
import com.platform.agriwatch.domain.repository.dataSensor.lastData.LastAirDataRepository;
import com.platform.agriwatch.domain.repository.dataSensor.lastData.LastSoilDataRepository;
import com.platform.agriwatch.domain.service.SensorDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorDataServiceImpl implements SensorDataService {

    private final AirDataRepository airDataRepository;
    private final SoilDataRepository soilDataRepository;
    private final SensorRepository sensorRepository;
    private final LastAirDataRepository lastAirDataRepository;
    private final LastSoilDataRepository lastSoilDataRepository;
    private final SensorWebSocketController sensorWebSocketController;

    @Override
    public SoilData addSoilData(SoilDataRequest soilDataRequest) {
        Optional<Sensor> sensor = sensorRepository.findBySensorName(soilDataRequest.getSensorName());
        if (sensor.isPresent()) {
            SoilData soilData = new SoilData(soilDataRequest);
            soilData.setSensor(sensor.get());
            soilDataRepository.save(soilData);

            // actualizar LastSoilData
            Optional<LastSoilData> existingLastSoilData = lastSoilDataRepository.findBySensor(sensor.get());
            LastSoilData lastSoilData = getLastSoilData(existingLastSoilData, soilData, sensor);
            lastSoilDataRepository.save(lastSoilData);

            //notificación de actualización
            sensorWebSocketController.sendSoilDataUpdate(new LastSoilDataResponse(soilData));
            return soilData;
        }
        return null;
    }

    @Override
    public AirData addAirData(AirDataRequest airDataRequest) {
        Optional<Sensor> sensor = sensorRepository.findBySensorName(airDataRequest.getSensorName());
        if (sensor.isPresent()) {
            AirData airData = new AirData(airDataRequest);
            airData.setSensor(sensor.get());
            airDataRepository.save(airData);

            // actualizar LastAirData
            Optional<LastAirData> existingLastAirData = lastAirDataRepository.findBySensor(sensor.get());
            LastAirData lastAirData = getLastAirData(existingLastAirData, airData, sensor);
            lastAirDataRepository.save(lastAirData);

            //notificación de actualización
            sensorWebSocketController.sendAirDataUpdate(new LastAirDataResponse(airData));
            return airData;
        }
        return null;
    }


    @Override
    public List<LastAirData> getLastAirData() {
        return lastAirDataRepository.findAll();
    }

    @Override
    public List<LastSoilData> getLastSoilData() {
        return lastSoilDataRepository.findAll();
    }


    //private metodos
    private static LastSoilData getLastSoilData(Optional<LastSoilData> existingLastSoilData, SoilData soilData, Optional<Sensor> sensor) {
        LastSoilData lastSoilData;
        if (existingLastSoilData.isPresent()) {
            lastSoilData = existingLastSoilData.get();
            lastSoilData.setSensorValue(soilData.getSensorValue());
            lastSoilData.setRecordDate(soilData.getRecordDate());
        } else {
            lastSoilData = new LastSoilData();
            lastSoilData.setSensor(sensor.get());
            lastSoilData.setSensorValue(soilData.getSensorValue());
            lastSoilData.setRecordDate(soilData.getRecordDate());
        }
        return lastSoilData;
    }
    private static LastAirData getLastAirData(Optional<LastAirData> existingLastAirData, AirData airData, Optional<Sensor> sensor) {
        LastAirData lastAirData;
        if (existingLastAirData.isPresent()) {
            lastAirData = existingLastAirData.get();
            lastAirData.setTemperatureValue(airData.getTemperatureValue());
            lastAirData.setHumidityValue(airData.getHumidityValue());
            lastAirData.setRecordDate(airData.getRecordDate());
        } else {
            lastAirData = new LastAirData();
            lastAirData.setSensor(sensor.get());
            lastAirData.setTemperatureValue(airData.getTemperatureValue());
            lastAirData.setHumidityValue(airData.getHumidityValue());
            lastAirData.setRecordDate(airData.getRecordDate());
        }
        return lastAirData;
    }

}
