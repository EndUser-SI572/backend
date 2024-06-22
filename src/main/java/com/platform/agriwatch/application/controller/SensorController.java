package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.sensor.*;
import com.platform.agriwatch.application.dto.response.sensor.LastAirDataResponse;
import com.platform.agriwatch.application.dto.response.sensor.LastSoilDataResponse;
import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;
import com.platform.agriwatch.domain.service.SensorDataService;
import com.platform.agriwatch.domain.service.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = "sensor/api/v1", produces = "application/json")
public class SensorController {

    private final SensorService sensorService;
    private final SensorDataService sensorDataService;

    @PostMapping("add")
    public ResponseEntity<Sensor> addSensor(@RequestBody SensorRequest sensorRequest) {

        Sensor createdSensor = sensorService.createSensor(new Sensor(sensorRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
    }

    @PutMapping("air")
    public ResponseEntity<Void> ambientData(@RequestBody AirDataRequest airDataRequest) {
        sensorDataService.addAirData(airDataRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("soil")
    public ResponseEntity<Void> soilData(@RequestBody SoilDataRequest soilDataRequest) {
        sensorDataService.addSoilData(soilDataRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/air/last")
    public ResponseEntity<List<LastAirDataResponse>> getLastAirData() {
        List<LastAirData> lastAirDataList = sensorDataService.getLastAirData();

        List<LastAirDataResponse> responseList = lastAirDataList.stream()
                .map(LastAirDataResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/soil/last")
    public ResponseEntity<List<LastSoilDataResponse>> getLastSoilData() {
        List<LastSoilData> lastSoilDataList = sensorDataService.getLastSoilData();

        List<LastSoilDataResponse> responseList = lastSoilDataList.stream()
                .map(LastSoilDataResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/irrigate")
    public ResponseEntity<String> activateIrrigate() {
        List<LastSoilData> lastSoilDataList = sensorDataService.getLastSoilData();

        boolean active = lastSoilDataList.stream()
                .anyMatch(lastSoilData -> lastSoilData.getSensorValue() < 10.0);

        return ResponseEntity.ok("{\"active\":" + active + "}");
    }

}
