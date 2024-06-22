package com.platform.agriwatch.domain.model;


import com.platform.agriwatch.application.dto.request.sensor.SensorRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="sensor_types")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String sensorName;

    @NotNull
    private String sensorType;

    private Boolean available;

    public Sensor(SensorRequest sensorRequest){
        sensorName = sensorRequest.getSensor_name();
        sensorType = sensorRequest.getSensor_type();
        available = true;
    }

}
