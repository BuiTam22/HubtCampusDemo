package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO;
import com.codecampushubt.NCKH2024TQQD.entity.CodingExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingExerciseRepository extends JpaRepository<CodingExercise, Long> {

    @Query("""
            SELECT new com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO
            (ce.exerciseID, ce.lesson.title, ce.title, ce.description, ce.programmingLanguage, ce.difficulty, ce.points, ce.slug)
            FROM CodingExercise ce
            WHERE ce.lesson.slug = :theSlug
            """)
    List<CodingExerciseDTO> getCodingExerciseDTOByLessonSlug(@Param("theSlug") String theSlug);

    @Query("""
            SELECT new com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO
            (ce.exerciseID, null, ce.title, ce.description, ce.programmingLanguage, ce.initialCode,
            ce.timeLimit, ce.memoryLimit, ce.difficulty, ce.points, ce.slug, ce.inputFormat, ce.outputFormat,
            ce.constraintName)
            FROM CodingExercise ce
            WHERE ce.slug = :theSlug
            """)
    CodingExerciseDetailDTO getCodingExerciseDetailDTOByExerciseSlug(@Param("theSlug") String theSlug);

    @Query("SELECT ce FROM CodingExercise ce WHERE ce.exerciseID = :exerciseID")
    CodingExercise getExerciseEntityByID(@Param("exerciseID") Long exerciseID);

}
