package com.monash.spector.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monash.spector.model.Plant;
import com.monash.spector.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {


    @RequestMapping("/access")
    public String checkAccess(){
        return "acess";
    }


    @RequestMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @PostMapping("/weather")
    public ResponseEntity<?> getWeatherData(@RequestBody String data ){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        Double longitude = jobj.get("longitude").getAsDouble();
        Double latitude = jobj.get("latitude").getAsDouble();
        return ResponseEntity.ok(WeatherService.getInstance().getWeatherData(longitude,latitude));
    }

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/more")
    public String aboutMore(){
        return "about-more";
    }



}
