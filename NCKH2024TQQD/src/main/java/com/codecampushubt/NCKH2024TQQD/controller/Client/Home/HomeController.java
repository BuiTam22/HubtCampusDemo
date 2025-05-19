package com.codecampushubt.NCKH2024TQQD.controller.Client.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping
    public String showHome(){
        return "ClientTemplates/home/index";
    }
}
