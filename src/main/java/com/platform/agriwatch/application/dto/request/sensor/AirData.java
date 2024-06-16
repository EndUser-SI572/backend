package com.platform.agriwatch.application.dto.request.sensor;

import lombok.Data;

@Data
public class AirData {

    private Float temperatureValue;
    private Float humidityValue;
    private String sensorName;

}
