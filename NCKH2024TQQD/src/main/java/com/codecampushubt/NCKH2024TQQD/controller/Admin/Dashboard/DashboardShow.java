package com.codecampushubt.NCKH2024TQQD.controller.Admin.Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/dashboard")
public class DashboardShow {
    @GetMapping("/show")
    public String showDashboard(){
        return "AdminTemplates/dashboard/show";
    }
}
