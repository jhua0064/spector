package com.monash.spector.service.repository;

import com.monash.spector.model.Occurence;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OccuRepository extends JpaRepository<Occurence,String> {

    List<Occurence> findAllByPlantId(Integer pId);
}
