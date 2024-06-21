package com.platform.agriwatch.domain.model.dataSensor;

import com.platform.agriwatch.application.dto.request.sensor.SoilDataRequest;
import com.platform.agriwatch.domain.model.Sensor;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="soil_sensor_data")
public class SoilData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double sensorValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_type_id", nullable = false)
    private Sensor sensor;

    @Column(nullable = false)
    private Date recordDate = new Date();


    public SoilData(SoilDataRequest soilDataRequest) {
        sensorValue = soilDataRequest.getSensorValue();
        recordDate = new Date();
    }
}
