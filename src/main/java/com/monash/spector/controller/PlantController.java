package com.monash.spector.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monash.spector.model.Guide;
import com.monash.spector.model.Occurence;
import com.monash.spector.model.Plant;
import com.monash.spector.service.GuideService;
import com.monash.spector.service.OccuService;
import com.monash.spector.service.PlantService;
import com.monash.spector.util.HibernateProxyTypeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        List<Plant> plants = plantService.listAll();
        List<Plant> plants = plantService.listPlantsByPage(0);
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

    @RequestMapping("/plantList")
    public String viewPlantList(){
        return "plant-list";
    }

    @PostMapping("/plantList/plants")
    public ResponseEntity<?> getPlantsDetail(@RequestBody String data ){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        int id = jobj.get("id").getAsInt();
        Plant plant  = plantService.getPlant(id);
        Guide guide = guideService.getGuide(id);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = builder.create();
        String json = "{\"name\":"+"\""+plant.getcName()+"\""+", \"link\":"+"\""+plant.getImgLink()+"\""+", \"guide\":"+gson.toJson(guide)+"}";

        return ResponseEntity.ok(json);
    }

}
