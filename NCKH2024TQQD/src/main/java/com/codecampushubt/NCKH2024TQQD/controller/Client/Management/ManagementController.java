package com.codecampushubt.NCKH2024TQQD.controller.Client.Management;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.ContestManagementShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.EditLessonDTO;
import com.codecampushubt.NCKH2024TQQD.service.LessonServices.LessonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/management")
public class ManagementController {
    private final LessonService lessonService;

    @Autowired
    public ManagementController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping("/contest")
    public String showCourse(Model model, HttpServletRequest request){
        List<ContestManagementShowDTO> contests = lessonService.getContestManagementShowDTO(3L, UserContext.getUsername());
        model.addAttribute("contests",contests);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/management/contest";
    }

    @GetMapping("/contest/create")
    public String createConteset(Model model, HttpServletRequest request){

        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/management/contest-create";
    }

    @GetMapping("/contest/edit/{lessonSlug}")
    public String editConteset(@PathVariable("lessonSlug") String theSlug, Model model, HttpServletRequest request){
        EditLessonDTO lesson = lessonService.getEditLessonDTO(3L, theSlug);
        model.addAttribute("lesson", lesson);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/management/contest-edit";
    }

    @GetMapping("/contest/score/{lessonSlug}")
    private String showScoreInLesson(@PathVariable("lessonSlug") String theSlug, Model model, HttpServletRequest request){
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/management/contest-score";
    }
}
