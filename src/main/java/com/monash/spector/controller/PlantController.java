package com.monash.spector.controller;

import com.google.gson.Gson;
import com.monash.spector.model.Student;
import com.monash.spector.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class PlantController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/plants")
    public String viewPlants(Model model){
        List<Student> students = studentService.listAll();
        String s = new Gson().toJson(students);
        model.addAttribute("students",s);
        return "gallery";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String plantDetails(@PathVariable int id, Model model){
        //Student student = studentService.get(id);
        model.addAttribute("id", id);
        return "detail";
    }

}
