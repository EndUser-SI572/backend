package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.domain.model.Plant;
import com.platform.agriwatch.domain.model.Sensor;
import com.platform.agriwatch.domain.model.User;
import com.platform.agriwatch.domain.repository.PlantRepository;
import com.platform.agriwatch.domain.repository.UserRepository;
import com.platform.agriwatch.domain.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;

    public PlantServiceImpl(PlantRepository plantRepository, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
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
            User user = plantToDelete.getUser();
            user.setNumberPlants(user.getNumberPlants() - 1);
            userRepository.save(user);

            plantRepository.delete(plantToDelete);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
