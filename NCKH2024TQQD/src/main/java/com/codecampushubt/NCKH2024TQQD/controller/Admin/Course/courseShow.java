package com.codecampushubt.NCKH2024TQQD.controller.Admin.Course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/course")
public class courseShow {
    @GetMapping("show")
    public String showCourse(){
        return "AdminTemplates/course/show";
    }
}
