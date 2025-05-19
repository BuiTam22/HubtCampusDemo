package com.codecampushubt.NCKH2024TQQD.controller.Client.Blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @GetMapping
    public String showHome(){
        return "ClientTemplates/blog/blog";
    }
}
