package com.platform.agriwatch.application.dto.request.sensor;

import com.platform.agriwatch.domain.model.Sensor;
import lombok.Data;

@Data
public class SensorRequest {

    private String sensor_name;
    private String sensor_type;

}
