package com.codecampushubt.NCKH2024TQQD.service.CodingSubmissionServices;

import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow;
import com.codecampushubt.NCKH2024TQQD.entity.CodingSubmission;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodingSubmissionService {
    CodingSubmission save(CodingSubmission codingSubmission);
    List<CodingSubmissionShow> getCodingSubmissionShowByUserName(String theUserName, String theSlug);
    List<CodingSubmissionShow> getCodingSubmissionShowBySlugExercise(String theSlug);

}
