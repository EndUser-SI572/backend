package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.domain.model.Plant;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PlantService {
    Plant create(Plant plant);
    Optional<Plant> getById(Long plantId);

    List<Plant> getPlantsByUserId(Long plantId);
    Plant update(Plant plant);

    ResponseEntity<?> delete(Long plantId);
}
