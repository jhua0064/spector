package com.monash.spector.service.repository;


import com.monash.spector.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant,Integer> {
}
