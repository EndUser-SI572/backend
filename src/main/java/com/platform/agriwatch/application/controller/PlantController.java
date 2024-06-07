package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.PlantRequest;
import com.platform.agriwatch.application.dto.request.UserRequest;
import com.platform.agriwatch.application.dto.response.PlantResponse;
import com.platform.agriwatch.application.dto.response.UserResponse;
import com.platform.agriwatch.domain.model.Plant;
import com.platform.agriwatch.domain.model.User;
import com.platform.agriwatch.domain.service.PlantService;

import com.platform.agriwatch.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/plant", produces = "application/json")
public class PlantController {

    private final PlantService plantService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PlantResponse> addPlant(@RequestBody PlantRequest plantRequest) {

        Optional<User> user = userService.getById(plantRequest.getUserId());
        if (user.isEmpty())
            return ResponseEntity.badRequest().build();

        Plant plant = plantRequest.toPlant();
        plant.setUser(user.get());
        plantService.create(plant);

        return new ResponseEntity<>(plant.toPlantResponse(), HttpStatus.CREATED);
    }

    @GetMapping("{plantId}")
    public ResponseEntity<PlantResponse>getPlantById(@PathVariable Long plantId) {
        Optional<Plant> plant = plantService.getById(plantId);

        if (plant.isPresent())
            return ResponseEntity.ok(plant.get().toPlantResponse());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlantResponse>> getPlantsByUserId(@PathVariable Long userId) {
        Optional<User> user = userService.getById(userId);

        if (user.isPresent()) {
            List<Plant> plants = plantService.getPlantsByUserId(userId);
            List<PlantResponse> plantResponses = plants.stream()
                    .map(Plant::toPlantResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(plantResponses);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{plantId}")
    public ResponseEntity<PlantResponse>updatePlant(@PathVariable Long plantId, @RequestBody PlantRequest plantRequest) {
        Optional<Plant> plant = plantService.getById(plantId);

        if (plant.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Plant newPlant = plant.get();
        newPlant.setName(plantRequest.getName());
        newPlant.setScientificName(plantRequest.getScientificName());
        newPlant.setIdealHumidity(plantRequest.getIdealHumidity());
        newPlant.setIdealTemperature(plantRequest.getIdealTemperature());
        newPlant.setImageUrl(plantRequest.getImageUrl());
        plantService.update(newPlant);

        return ResponseEntity.ok(newPlant.toPlantResponse());
    }

    @DeleteMapping("{plantId}")
    public ResponseEntity<?> deletePlant(@PathVariable Long plantId) {
        return plantService.delete(plantId);
    }
}
