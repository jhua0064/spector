package com.monash.spector.service;

import com.monash.spector.model.Occurence;
import com.monash.spector.service.repository.OccuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OccuService {
    @Autowired
    private OccuRepository occuRepo;

    public List<Occurence> getRecords(Integer pId){
        return occuRepo.findAllByPlantId(pId);
    }

}
