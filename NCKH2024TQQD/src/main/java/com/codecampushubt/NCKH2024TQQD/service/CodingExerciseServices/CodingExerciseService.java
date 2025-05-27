package com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices;

import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO;
import com.codecampushubt.NCKH2024TQQD.entity.CodingExercise;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodingExerciseService {
    List<CodingExerciseDTO> getCodingExerciseDTOByLessonSlug(String theSlug);

    // Lấy ra chi tiết bài tập dựa vào slug của bài tập
    CodingExerciseDetailDTO getCodingExerciseDetailDTOByExerciseSlug(String theSlug);

    CodingExercise getExerciseEntityByID(Long exerciseID);

    boolean isExerciseInContestLesson(Long exerciseID);

    Long getLessonIDByExerciseID(Long exerciseID);
}
