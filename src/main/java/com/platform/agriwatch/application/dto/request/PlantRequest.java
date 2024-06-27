package com.platform.agriwatch.application.dto.request;

import com.platform.agriwatch.domain.model.Plant;
import lombok.Data;

@Data
public class PlantRequest {

    private String name;
    private String scientificName;
    private String imageUrl;
    private Long userId;
}
