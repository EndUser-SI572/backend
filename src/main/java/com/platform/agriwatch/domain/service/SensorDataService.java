package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.dataSensor.AirData;
import com.platform.agriwatch.domain.model.dataSensor.SoilData;

public interface SensorDataService {

    SoilData addSoilData(SoilDataRequest soilDataRequest);
    AirData addAmbientData(AirDataRequest airDataRequest);

}
