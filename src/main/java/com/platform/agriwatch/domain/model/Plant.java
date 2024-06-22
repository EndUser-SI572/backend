package com.platform.agriwatch.domain.model;

import com.platform.agriwatch.application.dto.request.PlantRequest;
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

    private String imageUrl;

    @OneToOne
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Plant(PlantRequest plantRequest){
        name = plantRequest.getName();
        scientificName = plantRequest.getScientificName();
        imageUrl = plantRequest.getImageUrl();
    }
}
