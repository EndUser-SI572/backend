package com.platform.agriwatch.application.dto.request;

import com.platform.agriwatch.domain.model.Plant;
import com.platform.agriwatch.domain.model.User;
import lombok.Data;

@Data
public class PlantRequest {

    private String name;
    private String scientificName;
    private Float idealHumidity;
    private Float idealTemperature;
    private String imageUrl;
    private Long userId;

    public Plant toPlant(){
        return Plant.builder()
                .name(this.name)
                .scientificName(this.scientificName)
                .idealHumidity(this.idealHumidity)
                .idealTemperature(this.idealTemperature)
                .imageUrl(this.imageUrl)
                .build();
    }
}
