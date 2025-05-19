package com.codecampushubt.NCKH2024TQQD.controller.Client.Contest;

import java.util.ArrayList;
import java.util.List;

import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseListShowDTO;
import com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices.EssayExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.ContestShowDTO;
import com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices.CodingExerciseService;
import com.codecampushubt.NCKH2024TQQD.service.LessonServices.LessonService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contest")
public class ContestController {
    private final LessonService lessonService;
    private final CodingExerciseService codingExerciseService;
    private final EssayExerciseService essayExerciseService;

    @Autowired
    public ContestController(LessonService lessonService, CodingExerciseService codingExerciseService, EssayExerciseService essayExerciseService) {
        this.lessonService = lessonService;
        this.codingExerciseService = codingExerciseService;
        this.essayExerciseService = essayExerciseService;
    }

    @GetMapping("")
    public String showContest(Model model, HttpServletRequest request){
        // fix cứng moduleId là 3
        List<ContestShowDTO> contests = lessonService.getContestShowDTOByIsContest(3L);
        model.addAttribute("contests", contests);
        model.addAttribute("activePage", request.getRequestURI());
        return  "ClientTemplates/contest/contest";
    }

    @GetMapping("/{lesson-slug}")
    public String showCodingExerciseByLessonSlug(@PathVariable("lesson-slug") String theSlug, Model model, HttpServletRequest request){
        List<CodingExerciseDTO> exercises = codingExerciseService.getCodingExerciseDTOByLessonSlug(theSlug);
        exercises = exercises != null ? exercises : new ArrayList<>();
        model.addAttribute("exercises", exercises);
        model.addAttribute("activePage", request.getRequestURI());
        return  "ClientTemplates/contest/show";
    }

    @GetMapping("/type-essay")
    public String showEssayExercises(Model model, HttpServletRequest request){
        List<ContestShowDTO> essayContests = lessonService.getEssayContestShowDTOByIsContest(3L);
        model.addAttribute("contests", essayContests);
        model.addAttribute("activePage", request.getRequestURI());
        return  "ClientTemplates/contest/contest";
    }

    @GetMapping("/type-essay/{lesson-slug}")
    public String showEssayExerciseByLessonSlug(@PathVariable("lesson-slug") String theSlug, Model model, HttpServletRequest request){
        List<EssayExerciseListShowDTO> exercises = essayExerciseService.getEssayExerciseListShowDTOByLessonSlug(theSlug);
        exercises = exercises != null ? exercises : new ArrayList<>();
        model.addAttribute("exercises", exercises);
        model.addAttribute("activePage", request.getRequestURI());
        return  "ClientTemplates/contest/list-essay-show";
    }
}
