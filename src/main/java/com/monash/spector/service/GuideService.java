package com.monash.spector.service;

import com.monash.spector.model.Guide;
import com.monash.spector.service.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Help controller get guide related data from database
 */
@Service
@Transactional
public class GuideService {
    @Autowired
    private GuideRepository guideRepo;

    public Guide getGuide(Integer id){
        return guideRepo.getOne(id);
    }

    public List<Guide> getAllGuides(){
        return guideRepo.findAll();
    }

}
