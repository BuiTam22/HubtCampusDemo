package com.codecampushubt.NCKH2024TQQD.controller.Client.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginShow {
    @GetMapping("/show")
    public String showLogin(){
        return "ClientTemplates/login/show";
    }
}
