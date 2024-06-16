package com.platform.agriwatch.application.dto.request.sensor;

import lombok.Data;

@Data
public class SoilData {
    private String sensorName;
    private Float sensorValue;
}
