package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.dao.CodingExerciseRepository;
import com.codecampushubt.NCKH2024TQQD.dao.ExerciseTestCaseRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO;
import com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices.CodingExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coding-exercise")
public class RestCodingExercise {
   private final CodingExerciseService codingExerciseService;

   @Autowired
    public RestCodingExercise(CodingExerciseService codingExerciseService) {
        this.codingExerciseService = codingExerciseService;
    }


    @GetMapping("/find-by-lesson-id/{theSlug}")
    public List<CodingExerciseDTO> getCodingExerciseDTOByLessonSlug(@PathVariable("theSlug") String theSlug){
        return codingExerciseService.getCodingExerciseDTOByLessonSlug(theSlug   );
    }

    @GetMapping("/find-by-slug/{theSlug}")
    public CodingExerciseDetailDTO getCodingExerciseDetailDTOByExerciseSlug(@PathVariable("theSlug") String theSlug){
        return codingExerciseService.getCodingExerciseDetailDTOByExerciseSlug(theSlug);
    }
}
