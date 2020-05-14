package com.monash.spector.service;

import com.monash.spector.model.Plant;
import com.monash.spector.service.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlantService {
    @Autowired
    private PlantRepository plantRepo;

    public Plant getPlant(Integer id){
        return plantRepo.getOne(id);
    }

    public Page<Plant> listPlantsByPage(Integer page){
        Pageable pageable = PageRequest.of(page,32);

        return  plantRepo.findPlatsWithPage(pageable);
    }

    /**
     * find plants by filter
     * @param color
     * @param type
     * @param season
     * @param page page number
     * @return
     */
    public Page<Plant> listPlantsByFilter(String color, String type, String season, Integer page){
        Pageable pageable = PageRequest.of(page,32);
        return  plantRepo.findPlantsByFilter(color,type,season,pageable);
    }

    /**
     * find plants by search input plant name
     * @param name
     * @return
     */
    public List<Plant> listSearchedPlants(String name){
        return plantRepo.findBycNameContainingIgnoreCase(name);
    }

}
