package com.monash.spector.service.repository;

import com.monash.spector.model.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PestRepository extends JpaRepository<Pesticide, Integer> {

        List<Pesticide> findByNameContainingIgnoreCase(String param);
}
