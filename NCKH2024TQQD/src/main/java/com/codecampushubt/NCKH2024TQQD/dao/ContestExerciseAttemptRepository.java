package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.ContestExerciseAttempt.AttemptInfoDTO;
import com.codecampushubt.NCKH2024TQQD.entity.ContestExerciseAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestExerciseAttemptRepository extends JpaRepository<ContestExerciseAttempt, Long> {
    // LẤY RA SỐ LẦN LÀM BÀI (Attempt) CỦA USER
    @Query("""
    SELECT new com.codecampushubt.NCKH2024TQQD.dto.ContestExerciseAttempt.AttemptInfoDTO
    (cea.lesson.lessonID, cea.exerciseType, cea.attemptNumber)
    FROM ContestExerciseAttempt cea
    WHERE cea.user.userID = :userID 
      AND cea.exercise.exerciseID = :exerciseID
      AND cea.attemptNumber = (
          SELECT MAX(c2.attemptNumber)
          FROM ContestExerciseAttempt c2
          WHERE c2.user.userID = :userID AND c2.exercise.exerciseID = :exerciseID
      )
    """)
    AttemptInfoDTO getAttemptInfoDTOByuserIDAndExerciseID(@Param("userID") Long userID, @Param("exerciseID") Long exerciseID);

}
