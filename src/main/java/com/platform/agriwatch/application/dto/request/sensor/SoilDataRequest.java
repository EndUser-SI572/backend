package com.platform.agriwatch.application.dto.request.sensor;

import lombok.Data;

@Data
public class SoilDataRequest {
    private String sensorName;
    private Double sensorValue;

}
