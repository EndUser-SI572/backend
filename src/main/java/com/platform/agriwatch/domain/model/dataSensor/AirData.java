package com.platform.agriwatch.domain.model.dataSensor;

import com.platform.agriwatch.application.dto.request.sensor.AirDataRequest;
import com.platform.agriwatch.domain.model.Sensor;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="air_sensor_data")
public class AirData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double temperatureValue;

    @Column(nullable = false)
    private Double humidityValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_type_id", nullable = false)
    private Sensor sensor;

    @Column(nullable = false)
    private Date recordDate = new Date();


    public AirData(AirDataRequest airDataRequest) {
        temperatureValue = airDataRequest.getTemperatureValue();
        humidityValue = airDataRequest.getHumidityValue();
        recordDate = new Date();
    }
}
