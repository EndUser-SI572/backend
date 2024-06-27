package com.platform.agriwatch.application.dto.response.sensor;

import com.platform.agriwatch.domain.model.dataSensor.SoilData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;
import lombok.Data;

import java.util.Date;

@Data
public class LastSoilDataResponse {

    private Double sensorValue;
    private String sensorName;
    private Date recordDate;

    public LastSoilDataResponse(LastSoilData lastSoilData) {
        sensorValue = lastSoilData.getSensorValue();
        sensorName = lastSoilData.getSensor().getSensorName();
        recordDate = lastSoilData.getRecordDate();
    }
    public LastSoilDataResponse(SoilData soilData) {
        sensorValue = soilData.getSensorValue();
        sensorName = soilData.getSensor().getSensorName();
        recordDate = soilData.getRecordDate();
    }

}
