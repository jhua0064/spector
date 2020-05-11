package com.monash.spector.service;

import com.monash.spector.model.Pesticide;
import com.monash.spector.service.repository.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * deal with customer data result from Jpa
 */
@Service
@Transactional
public class PestService {
    @Autowired
    private PestRepository pestRepo;

    public List<Pesticide> getSearchedResult(String searchParam){
        return  pestRepo.findByNameContainingIgnoreCase(searchParam);
    }
}
