package com.platform.agriwatch.domain.model.dataSensor.lastData;

import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.dataSensor.SoilData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Date;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="last_soil_sensor_data")
public class LastSoilData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double sensorValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_type_id", nullable = false)
    private Sensor sensor;

    @Column(nullable = false)
    private Date recordDate;

}