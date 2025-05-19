package com.codecampushubt.NCKH2024TQQD.controller.Client.Course;

import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseModuleDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import com.codecampushubt.NCKH2024TQQD.service.CourseServices.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("")
    public String showCourse(Model model, HttpServletRequest request){
        List<CourseShowDTO> courses = courseService.getCourseShowDTO();
        model.addAttribute("courses", courses);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/course/show";
    }

    @GetMapping("/{slug}")
    public String showDetailCourse(@PathVariable("slug") String theSlug,Model model, HttpServletRequest request){
        List<CourseModuleDTO> courseModules = courseService.getCourseModuleByCourseSlug(theSlug);
        List<CourseShowDTO> courses = courseService.getCourseShowDTO();
        model.addAttribute("courses", courses);
        model.addAttribute("courseModules", courseModules);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/course/course-module";
    }

}
