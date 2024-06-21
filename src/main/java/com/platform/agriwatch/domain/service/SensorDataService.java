package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.AirData;
import com.platform.agriwatch.domain.model.dataSensor.SoilData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;

import java.util.Optional;

public interface SensorDataService {

    SoilData addSoilData(SoilDataRequest soilDataRequest);
    AirData addAirData(AirDataRequest airDataRequest);

    Optional<LastAirData> getLastAirData(String sensorName);

    Optional<LastSoilData> getLastSoilData(String sensorName);

}
