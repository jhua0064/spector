package com.monash.spector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/more")
    public String aboutMore(){
        return "about-more";
    }

    @RequestMapping("/insect")
    public String contactPage(){
        return "insect";
    }


}
