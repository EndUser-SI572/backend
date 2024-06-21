package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.application.dto.request.sensor.SensorRequest;
import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.AirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;
import com.platform.agriwatch.domain.service.SensorDataService;
import com.platform.agriwatch.domain.service.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping(value = "sensor/api/v1", produces = "application/json")
public class SensorController {

    private final SensorService sensorService;
    private final SensorDataService sensorDataService;

    @PostMapping("add")
    public ResponseEntity<Sensor> addSensor(@RequestBody SensorRequest sensorRequest) {

        Sensor createdSensor = sensorService.createSensor(sensorRequest.toSensor());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
    }

    @PutMapping("air")
    public ResponseEntity<AirData> ambientData(@RequestBody AirDataRequest airDataRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                sensorDataService.addAirData(airDataRequest));
    }


    @PutMapping("soil")
    public ResponseEntity<Void> soilData(@RequestBody List<SoilDataRequest> soilDataRequestList) {

        soilDataRequestList.forEach(sensorDataService::addSoilData);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("air/last/{sensorName}")
    public ResponseEntity<LastAirData> getLastAirData(@PathVariable String sensorName) {
        return sensorDataService.getLastAirData(sensorName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("soil/last/{sensorName}")
    public ResponseEntity<LastSoilData> getLastSoilData(@PathVariable String sensorName) {
        return sensorDataService.getLastSoilData(sensorName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("irrigate")
    public String activateIrrigate() {
        Random random = new Random();
        boolean active = random.nextBoolean();

        return "{\"active\":" + active + "}";

    }

}
