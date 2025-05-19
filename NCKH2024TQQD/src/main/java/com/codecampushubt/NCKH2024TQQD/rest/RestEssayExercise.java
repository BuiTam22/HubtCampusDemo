package com.codecampushubt.NCKH2024TQQD.rest;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codecampushubt.NCKH2024TQQD.service.ContestExerciseAttemptServices.ContestExerciseAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseDetailShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseListShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseSubmissionRequest;
import com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices.EssayExerciseService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/essay-exercise")
public class RestEssayExercise {


    private final EssayExerciseService essayExerciseService;

    @Autowired
    public RestEssayExercise(EssayExerciseService essayExerciseService, ContestExerciseAttemptService contestExerciseAttemptService) {
        this.essayExerciseService = essayExerciseService;
    }

    @GetMapping("/{lessonSlug}")
    public List<EssayExerciseListShowDTO> getEssayExerciseListShowDTOByLessonSlug(@PathVariable("lessonSlug") String theSlug) {
        return essayExerciseService.getEssayExerciseListShowDTOByLessonSlug(theSlug);
    }

    @GetMapping("/problem/{exerciseSlug}")
    public EssayExerciseDetailShowDTO getEssayExerciseDetailShowDTOBySlug(@PathVariable("exerciseSlug") String theSlug) {
        return essayExerciseService.getEssayExerciseDetailShowDTOBySlug(theSlug);
    }

}
