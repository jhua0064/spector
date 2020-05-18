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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlantController {

    @Autowired
    private PlantService plantService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private OccuService occuService;


    /**
     * Go to plants gallery page,set first page plants data
     * @param model
     * @return
     */
    @RequestMapping("/plants")
    public String viewPlants(Model model){
        Page<Plant> plants = plantService.listPlantsByPage(0);
        List<Plant> topPlants = plantService.getTopSelectedPlants();
        Gson gson = new Gson();
        int totalPages = plants.getTotalPages();
        model.addAttribute("plants",gson.toJson(plants.getContent()));
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("topPlants", topPlants);
        return "gallery";
    }

    /**
     * get plant and guide detail by id
     * @param id  plant id
     * @param model
     * @return
     */
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

    /**
     * go to favorite list page
     * @param model
     * @return
     */
    @RequestMapping("/plantList")
    public String viewPlantList(Model model){
        List<Plant> topPlants = plantService.getTopSelectedPlants();
        model.addAttribute("topPlants", topPlants);
        return "plant-list";
    }

    /**
     * get plant detail and add it to favorite list from plant gallery page
     * @param data
     * @return
     */
    @PostMapping("/plantList/plants")
    public ResponseEntity<?> getPlantsDetail(@RequestBody String data ){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        int id = jobj.get("id").getAsInt();
        plantService.updateTopPlant(id);
        Plant plant  = plantService.getPlant(id);
        Guide guide = guideService.getGuide(id);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = builder.create();
        String json = "{\"name\":"+"\""+plant.getcName()+"\""+", \"link\":"+"\""+plant.getImgLink()+"\""+", \"guide\":"+gson.toJson(guide)+"}";

        return ResponseEntity.ok(json);
    }

    /**
     * get plant detail and add it to favorite list from plant detail page
     * @param data
     * @return
     */
    @PostMapping("/plantList/detail")
    public ResponseEntity<?> getPlantDetail(@RequestBody String data ){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        int id = jobj.get("id").getAsInt();
        Integer integer = plantService.updateTopPlant(id);

        return ResponseEntity.ok(integer);
    }

    /**
     * get filtered plant data to front page
     * @param data
     * @return
     */
    @PostMapping("/page/filter")
    public ResponseEntity<?>  getFilterPlants(@RequestBody String data){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        String color = jobj.get("color").getAsString();
        color = ("all".equals(color)) ? "" : color;
        String type = jobj.get("type").getAsString();
        type = ("all".equals(type)) ? "" : type;
        String season = jobj.get("month").getAsString();
        season = ("all".equals(season)) ? "" : season;
        int page = jobj.get("page").getAsInt();
        Page<Plant> plants = plantService.listPlantsByFilter(color, type, season, page-1);
        String s = new Gson().toJson(plants.getContent());
        s = "{\"page\":"+plants.getTotalPages()+", \"plants\":"+s+"}";
        return ResponseEntity.ok(s);
    }

    /**
     *
     * get plants data by searching plant name
     * @param data
     * @return
     */
    @PostMapping("/plant/search")
    public ResponseEntity<?> getSearchedPlants(@RequestBody String data ){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        String name = jobj.get("name").getAsString();
        List<Plant> plants  = plantService.listSearchedPlants(name);

        return ResponseEntity.ok(new Gson().toJson(plants));
    }
}
