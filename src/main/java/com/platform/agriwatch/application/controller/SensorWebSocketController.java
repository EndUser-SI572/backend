package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.response.sensor.LastAirDataResponse;
import com.platform.agriwatch.application.dto.response.sensor.LastSoilDataResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SensorWebSocketController {

    private final SimpMessagingTemplate template;

    public SensorWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendAirDataUpdate(LastAirDataResponse lastAirDataResponse) {
        this.template.convertAndSend("/topic/airData", lastAirDataResponse);
    }

    public void sendSoilDataUpdate(LastSoilDataResponse lastSoilDataResponse) {
        this.template.convertAndSend("/topic/soilData", lastSoilDataResponse);
    }

}
