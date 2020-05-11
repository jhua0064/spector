package com.monash.spector.service;

import com.monash.spector.model.Plant;
import com.monash.spector.service.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Plant> listAll(){
        return plantRepo.findAll();
    }

    public Plant getPlant(Integer id){
        return plantRepo.getOne(id);
    }

    public List<Plant> listPlantsByPage(Integer page){
        Pageable pageable = PageRequest.of(page,140);
        return  plantRepo.findPlatsWithPage(pageable);
    }

}
