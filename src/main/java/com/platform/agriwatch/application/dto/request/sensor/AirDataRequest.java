package com.platform.agriwatch.application.dto.request.sensor;

import lombok.Data;

@Data
public class AirDataRequest {

    private Double temperatureValue;
    private Double humidityValue;
    private String sensorName;


}
