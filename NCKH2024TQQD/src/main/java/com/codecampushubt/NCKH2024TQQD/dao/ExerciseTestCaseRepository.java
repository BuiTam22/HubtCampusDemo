package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO;
import com.codecampushubt.NCKH2024TQQD.entity.ExerciseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExerciseTestCaseRepository extends JpaRepository<ExerciseTestCase, Long> {
    @Query("""
            SELECT new com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO
            (et.input, et.expectedOutput, et.isPublic, et.score)
            FROM ExerciseTestCase et 
            WHERE et.codingExercise.exerciseID = :theID
            """)
    Set<ExerciseTestCasesDTO> getExerciseTestCasesDTOByExerciseID(@Param("theID") Long exerciseID);
}