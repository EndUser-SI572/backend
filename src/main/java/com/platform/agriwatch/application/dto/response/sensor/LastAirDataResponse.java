package com.platform.agriwatch.application.dto.response.sensor;

import com.platform.agriwatch.domain.model.dataSensor.AirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import lombok.Data;

import java.util.Date;

@Data
public class LastAirDataResponse {
    private Double temperatureValue;
    private Double humidityValue;
    private String sensorName;
    private Date recordDate;

    public LastAirDataResponse(LastAirData lastAirData) {
        temperatureValue=lastAirData.getTemperatureValue();
        humidityValue=lastAirData.getHumidityValue();
        sensorName=lastAirData.getSensor().getSensorName();
        recordDate=lastAirData.getRecordDate();
    }
    public LastAirDataResponse(AirData lastAir) {
        temperatureValue=lastAir.getTemperatureValue();
        humidityValue=lastAir.getHumidityValue();
        sensorName=lastAir.getSensor().getSensorName();
        recordDate=lastAir.getRecordDate();
    }
}
