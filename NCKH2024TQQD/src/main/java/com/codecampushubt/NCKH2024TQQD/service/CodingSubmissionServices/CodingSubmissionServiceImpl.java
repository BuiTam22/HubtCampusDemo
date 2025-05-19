package com.codecampushubt.NCKH2024TQQD.service.CodingSubmissionServices;

import com.codecampushubt.NCKH2024TQQD.dao.CodingSubmissionRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission.CodingSubmissionShow;
import com.codecampushubt.NCKH2024TQQD.entity.CodingSubmission;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingSubmissionServiceImpl implements CodingSubmissionService{
    private final CodingSubmissionRepository codingSubmissionRepository;

    @Autowired
    public CodingSubmissionServiceImpl(CodingSubmissionRepository codingSubmissionRepository) {
        this.codingSubmissionRepository = codingSubmissionRepository;
    }

    @Override
    @Transactional
    public CodingSubmission save(CodingSubmission codingSubmission) {

        return codingSubmissionRepository.save(codingSubmission);
    }

    @Override
    public List<CodingSubmissionShow> getCodingSubmissionShowByUserName(String theUserName, String theSlug) {
        return codingSubmissionRepository.getCodingSubmissionShowByUserName(theUserName, theSlug);
    }

    @Override
    public List<CodingSubmissionShow> getCodingSubmissionShowBySlugExercise(String theSlug) {
        return codingSubmissionRepository.getCodingSubmissionShowBySlugExercise(theSlug);
    }
}
