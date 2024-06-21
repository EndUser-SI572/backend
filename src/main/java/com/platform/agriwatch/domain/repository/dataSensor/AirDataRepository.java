package com.platform.agriwatch.domain.repository.dataSensor;

import com.platform.agriwatch.domain.model.dataSensor.AirData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirDataRepository extends JpaRepository<AirData, Long> {


}
