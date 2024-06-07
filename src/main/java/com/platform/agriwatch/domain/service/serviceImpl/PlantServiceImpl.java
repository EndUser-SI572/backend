package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.domain.model.Plant;
import com.platform.agriwatch.domain.repository.PlantRepository;
import com.platform.agriwatch.domain.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    public PlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public Plant create(Plant plant) {
        return plantRepository.save(plant);
    }

    @Override
    public Optional<Plant> getById(Long plantId) {
        return plantRepository.findById(plantId);
    }

    @Override
    public List<Plant> getPlantsByUserId(Long userId) {
        return plantRepository.findPlantsByUserId(userId);
    }

    @Override
    public Plant update(Plant plant) {
        return plantRepository.save(plant);
    }

    @Override
    public ResponseEntity<?> delete(Long plantId) {
        Optional<Plant> plantOptional = plantRepository.findById(plantId);

        if (plantOptional.isPresent()) {
            Plant plantToDelete = plantOptional.get();
            plantRepository.delete(plantToDelete);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
