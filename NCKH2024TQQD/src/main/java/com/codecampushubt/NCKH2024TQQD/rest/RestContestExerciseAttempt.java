package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.dto.ContestExerciseAttempt.AttemptInfoDTO;
import com.codecampushubt.NCKH2024TQQD.service.ContestExerciseAttemptServices.ContestExerciseAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contest-exercise-attempt")
public class RestContestExerciseAttempt {
    private final ContestExerciseAttemptService contestExerciseAttemptService;

    @Autowired
    public RestContestExerciseAttempt(ContestExerciseAttemptService contestExerciseAttemptService) {
        this.contestExerciseAttemptService = contestExerciseAttemptService;
    }


    @GetMapping("/find-max-attempt-of-exercise-in-lesson/{userID}/{exerciseID}/{exerciseType}")
    AttemptInfoDTO getAttemptInfoDTOByuserIDAndExerciseID(@PathVariable("userID") Long userID, @PathVariable("exerciseID") Long exerciseID, @PathVariable("exerciseType") String exerciseType){
        return contestExerciseAttemptService.getAttemptInfoDTOByuserIDAndExerciseID(userID, exerciseID, exerciseType);
    }
}
