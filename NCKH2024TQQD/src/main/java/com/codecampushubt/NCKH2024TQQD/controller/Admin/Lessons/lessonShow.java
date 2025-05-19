package com.codecampushubt.NCKH2024TQQD.controller.Admin.Lessons;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class lessonShow {
    @GetMapping("/admin/lesson/show")
    public String lessonShow() {
        return "AdminTemplates/lesson/show";
    }
}
