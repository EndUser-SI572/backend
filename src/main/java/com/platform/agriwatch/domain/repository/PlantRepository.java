package com.platform.agriwatch.domain.repository;

import com.platform.agriwatch.domain.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findPlantsByUserId(Long userId);

}
