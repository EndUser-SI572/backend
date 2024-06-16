package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.sensor.AirData;
import com.platform.agriwatch.application.dto.request.sensor.SoilData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "sensor/api/v1", produces = "application/json")
public class SensorController {


    @PutMapping("air")
    public ResponseEntity<AirData> ambientData(@RequestBody AirData airData) {
        return ResponseEntity.ok(airData);
    }

    @PutMapping("soil")
    public ResponseEntity<List<SoilData>> soilData(@RequestBody List<SoilData> soilDataList) {
        return ResponseEntity.ok(soilDataList);
    }

    @GetMapping("irrigate")
    public Boolean activateIrrigate() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
