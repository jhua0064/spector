package com.monash.spector.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.monash.spector.model.Student;
import com.monash.spector.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/access")
    public String checkAccess(){
        return "acess";
    }


    @RequestMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @RequestMapping("/plants")
    public String viewPlants(Model model){
        List<Student> students = studentService.listAll();
        String s = new Gson().toJson(students);
        model.addAttribute("students",s);
        return "gallery";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/contact")
    public String contactPage(){
        return "contact-us";
    }


}
