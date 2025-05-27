package com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices;


import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseDetailShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseListShowDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EssayExerciseService {
    List<EssayExerciseListShowDTO> getEssayExerciseListShowDTOByLessonSlug(String theSlug);
    EssayExerciseDetailShowDTO getEssayExerciseDetailShowDTOBySlug(String theSlug);
    String getExpectedAnswerOfEssayExerciseByExerciseID(Long theID);
    Long getLessonIDByExerciseID(Long exerciseID);
}
