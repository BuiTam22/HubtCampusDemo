package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow;
import com.codecampushubt.NCKH2024TQQD.service.CodingSubmissionServices.CodingSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class RestSubmission {
    private final CodingSubmissionService codingSubmissionService;

    @Autowired
    public RestSubmission(CodingSubmissionService codingSubmissionService) {
        this.codingSubmissionService = codingSubmissionService;
    }


    @GetMapping("/user/{theUserName}")
    public List<CodingSubmissionShow> getCodingSubmissionShowByUserName(@PathVariable String theUserName, @RequestParam String theSlug){
        return codingSubmissionService.getCodingSubmissionShowByUserName(theUserName, theSlug);
    }

    @GetMapping("/exercise/{theSlugExercise}")
    public List<CodingSubmissionShow> getCodingSubmissionShowBySubmissionID(@PathVariable String theSlugExercise){
        return codingSubmissionService.getCodingSubmissionShowBySlugExercise(theSlugExercise);

    }
}
