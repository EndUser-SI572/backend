package com.platform.agriwatch.application.dto.response;

import com.platform.agriwatch.domain.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantResponse {

    private Long id;
    private String name;
    private String scientificName;
    private Double humidity;
    private Double temperature;
    private String imageUrl;
    private Long userId;

    public PlantResponse(Plant plant) {
        id = plant.getId();
        name = plant.getName();
        scientificName = plant.getScientificName();
        humidity = 0.0;
        temperature= 0.0;
        imageUrl = plant.getImageUrl();
        userId = plant.getUser().getId();
    }
}
