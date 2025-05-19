package com.codecampushubt.NCKH2024TQQD.controller.Admin.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user")
public class UserShow {
    @GetMapping("/show")
    public String showUser(){
        return "AdminTemplates/user/show";
    }

}
//trar ea ddc json+