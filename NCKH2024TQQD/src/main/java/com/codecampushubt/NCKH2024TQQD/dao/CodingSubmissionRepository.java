package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow;
import com.codecampushubt.NCKH2024TQQD.entity.CodingSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingSubmissionRepository extends JpaRepository<CodingSubmission, Long> {
    @Query("""
            SELECT new com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow
            (cb.exercise.title, cb.user.userName, cb.code, cb.language, cb.status, cb.testCasesPassed, cb.totalTestCases, cb.score, cb.submittedAt)
            FROM CodingSubmission cb
            WHERE cb.user.userName = :userName AND cb.exercise.slug = :theSlug
            ORDER BY cb.submittedAt DESC
            """)
    List<CodingSubmissionShow> getCodingSubmissionShowByUserName(@Param("userName") String theUserName, @Param("theSlug") String theSlug);

    @Query("""
            SELECT new com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow
            (cb.exercise.title, cb.user.userName, cb.code, cb.language, cb.status, cb.testCasesPassed, cb.totalTestCases, cb.score, cb.submittedAt)
            FROM CodingSubmission cb
            WHERE cb.exercise.slug = :theSlug
            ORDER BY cb.submittedAt DESC
            """)
    List<CodingSubmissionShow> getCodingSubmissionShowBySlugExercise(@Param("theSlug") String theSlug);
}
