package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.PlantRequest;
import com.platform.agriwatch.application.dto.response.PlantResponse;
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

        Plant plant = new Plant(plantRequest);
        plant.setUser(user.get());
        plantService.create(plant);

        user.get().setNumberPlants(user.get().getNumberPlants()+1);
        userService.update(user.get());

        return new ResponseEntity<>(new PlantResponse(plant), HttpStatus.CREATED);
    }

    @GetMapping("{plantId}")
    public ResponseEntity<PlantResponse>getPlantById(@PathVariable Long plantId) {
        Optional<Plant> plant = plantService.getById(plantId);

        return plant.map(
                value -> ResponseEntity.ok(new PlantResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlantResponse>> getPlantsByUserId(@PathVariable Long userId) {
        Optional<User> user = userService.getById(userId);

        if (user.isPresent()) {
            List<Plant> plants = plantService.getPlantsByUserId(userId);
            List<PlantResponse> plantResponses = plants.stream()
                    .map(PlantResponse::new)
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
        newPlant.setImageUrl(plantRequest.getImageUrl());
        plantService.update(newPlant);

        return ResponseEntity.ok(new PlantResponse(newPlant));
    }

    @DeleteMapping("{plantId}")
    public ResponseEntity<?> deletePlant(@PathVariable Long plantId) {
        return plantService.delete(plantId);
    }
}
