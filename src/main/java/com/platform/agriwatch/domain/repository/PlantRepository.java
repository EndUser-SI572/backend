package com.platform.agriwatch.domain.repository;

import com.platform.agriwatch.domain.model.Plant;
import com.platform.agriwatch.domain.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findPlantsByUserId(Long userId);

    Plant findBySensor(Sensor sensor);

}
