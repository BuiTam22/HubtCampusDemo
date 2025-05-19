package com.codecampushubt.NCKH2024TQQD.controller.Client.EssayExercise;

import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseDetailShowDTO;
import com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices.EssayExerciseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/practice/lesson/type-essay")
public class EssayExerciseController {
    private final EssayExerciseService essayExerciseService;

    @Autowired
    public EssayExerciseController(EssayExerciseService essayExerciseService) {
        this.essayExerciseService = essayExerciseService;
    }

    @GetMapping("/{lesson-slug}")
    public String showCodingExerciseByLessonSlug(@PathVariable("lesson-slug") String theSlug, Model model, HttpServletRequest request){

        model.addAttribute("activePage", request.getRequestURI());
        return  "ClientTemplates/essay-exercise/show";
    }

    @GetMapping("/problem/{slug}")
    public String showExerciseDetailBySlug(@PathVariable("slug") String theSlug, Model model, HttpServletRequest request){
        EssayExerciseDetailShowDTO essayExercise = essayExerciseService.getEssayExerciseDetailShowDTOBySlug(theSlug);
        model.addAttribute("essayExercise", essayExercise);
        model.addAttribute("slug", theSlug);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/essay-exercise/problem";
    }
}
