package com.codecampushubt.NCKH2024TQQD.service.ContestExerciseAttemptServices;

import com.codecampushubt.NCKH2024TQQD.dto.ContestExerciseAttempt.AttemptInfoDTO;
import com.codecampushubt.NCKH2024TQQD.entity.ContestExerciseAttempt;
import org.springframework.data.repository.query.Param;

public interface ContestExerciseAttemptService {
    AttemptInfoDTO getAttemptInfoDTOByuserIDAndExerciseID(Long userID, Long exerciseID, String exerciseType);
    ContestExerciseAttempt save(ContestExerciseAttempt contestExerciseAttempt);
}
