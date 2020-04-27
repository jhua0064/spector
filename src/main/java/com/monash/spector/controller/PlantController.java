package com.monash.spector.controller;

import com.google.gson.Gson;
import com.monash.spector.model.Guide;
import com.monash.spector.model.Occurence;
import com.monash.spector.model.Plant;
import com.monash.spector.service.GuideService;
import com.monash.spector.service.OccuService;
import com.monash.spector.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlantController {

    @Autowired
    private PlantService plantService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private OccuService occuService;


    @RequestMapping("/plants")
    public String viewPlants(Model model){
        List<Plant> plants = plantService.listAll();
        model.addAttribute("plants",new Gson().toJson(plants));
        return "gallery";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String plantDetails(@PathVariable int id, Model model){
        Guide guide = guideService.getGuide(id);
        Plant plant = plantService.getPlant(id);
        List<Occurence> records = occuService.getRecords(id);
        model.addAttribute("guide", guide);
        model.addAttribute("plant", plant);
        model.addAttribute("records",new Gson().toJson(records));
        return "detail";
    }

}
