package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastAirData;
import com.platform.agriwatch.domain.model.dataSensor.lastData.LastSoilData;

import java.util.List;

public interface SensorDataService {

    void addSoilData(List<SoilDataRequest> soilDataList);
    void addAirData(AirDataRequest airDataRequest);

    List<LastAirData> getLastAirData();

    List<LastSoilData> getLastSoilData();

}
