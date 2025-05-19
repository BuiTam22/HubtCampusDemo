package com.codecampushubt.NCKH2024TQQD.controller.Client.CodingExercise;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow;
import com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices.CodingExerciseService;
import com.codecampushubt.NCKH2024TQQD.service.CodingSubmissionServices.CodingSubmissionService;
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
@RequestMapping("/practice/lesson")
public class CodingExerciseController {

    private final CodingExerciseService codingExerciseService;
    private final CodingSubmissionService codingSubmissionService;

    private String exerciseSlug;

    @Autowired
    public CodingExerciseController(CodingExerciseService codingExerciseService, CodingSubmissionService codingSubmissionService) {
        this.codingExerciseService = codingExerciseService;
        this.codingSubmissionService = codingSubmissionService;
    }

    @GetMapping("/{lesson-slug}")
    public String showCodingExerciseByLessonSlug(@PathVariable("lesson-slug") String theSlug, Model model, HttpServletRequest request){
        List<CodingExerciseDTO> exercises = codingExerciseService.getCodingExerciseDTOByLessonSlug(theSlug);
        exercises = exercises != null ? exercises : new ArrayList<>();
        model.addAttribute("exercises", exercises);
        model.addAttribute("activePage", request.getRequestURI());
        return  "ClientTemplates/coding-exercise/show";
    }

    @GetMapping("/problem/{slug}")
    public String showExerciseDetailBySlug(@PathVariable("slug") String theSlug, Model model, HttpServletRequest request){
        CodingExerciseDetailDTO exercise = codingExerciseService.getCodingExerciseDetailDTOByExerciseSlug(theSlug);
        model.addAttribute("exercise", exercise);
        model.addAttribute("slug", theSlug);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/coding-exercise/problem";
    }

    @GetMapping("/submissions/{slug}")
    public String showExerciseSubmissions(@PathVariable("slug") String theSlug, Model model, HttpServletRequest request){
        List<CodingSubmissionShow> submissions = codingSubmissionService.getCodingSubmissionShowByUserName(UserContext.getUsername(), theSlug);
        model.addAttribute("submissions", submissions);
        model.addAttribute("slug", theSlug);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/coding-exercise/submission";
    }

    @GetMapping("/leaderboard/{slug}")
    public String showLeaderBoard(@PathVariable("slug") String theSlug, Model model, HttpServletRequest request){

        List<CodingSubmissionShow> submissions = codingSubmissionService.getCodingSubmissionShowBySlugExercise(theSlug);
        model.addAttribute("submissions", submissions);
        model.addAttribute("slug", theSlug);
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/coding-exercise/leaderboard";
    }

    @GetMapping("/tutorial/{slug}")
    public String showTutorial(@PathVariable("slug") String theSlug, Model model, HttpServletRequest request){
        model.addAttribute("activePage", request.getRequestURI());
        return "ClientTemplates/coding-exercise/tutorial";
    }


}