package com.monash.spector.service.repository;


import com.monash.spector.model.Plant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant,Integer> {

        @Query(value = "select p from Plant p order by p.id")
        List<Plant> findPlatsWithPage(Pageable pageable);
}
