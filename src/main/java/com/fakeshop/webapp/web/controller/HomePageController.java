package com.fakeshop.webapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public String homepage(Model model){
        model.addAttribute("hello", "hi");
        return "home";
    }

    @RequestMapping("/loginadmin")
    public String adminLogin(Model model){
        model.addAttribute("hello", "hi");
        return "home";
    }

    @RequestMapping("/admin/hello")
    public String adminTest(Model model){
        model.addAttribute("hello", "hi");
        return "home";
    }

    @RequestMapping("/login")
    public String userLogin(Model model){

        return "login";
    }

    @RequestMapping("/account/hello")
    public String userTest(Model model){
        model.addAttribute("hello", "hi");
        return "home";
    }


}
