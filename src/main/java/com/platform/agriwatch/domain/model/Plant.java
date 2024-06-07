package com.platform.agriwatch.domain.model;

import com.platform.agriwatch.application.dto.response.PlantResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String scientificName;

    @NotNull
    private Float idealHumidity;

    @NotNull
    private Float idealTemperature;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public PlantResponse toPlantResponse(){
        return PlantResponse.builder()
                .id(this.id)
                .name(this.name)
                .scientificName(this.scientificName)
                .idealHumidity(this.idealHumidity)
                .idealTemperature(this.idealTemperature)
                .imageUrl(this.imageUrl)

                .build();
    }
}
