package com.platform.agriwatch.application.dto.response;

import com.platform.agriwatch.domain.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlantResponse {

    private Long id;
    private String name;
    private String scientificName;
    private Float idealHumidity;
    private Float idealTemperature;
    private String imageUrl;
}
