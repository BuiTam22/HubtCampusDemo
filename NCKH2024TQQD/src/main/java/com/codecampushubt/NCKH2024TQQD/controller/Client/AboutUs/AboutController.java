package com.codecampushubt.NCKH2024TQQD.controller.Client.AboutUs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {
    @GetMapping
    public String showHome(){
        return "ClientTemplates/about-us/about-us";
    }
}
