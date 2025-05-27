package com.codecampushubt.NCKH2024TQQD.service.EssaySubmissionServices;

import com.codecampushubt.NCKH2024TQQD.dao.EssaySubmissionRepository;
import com.codecampushubt.NCKH2024TQQD.entity.EssaySubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssaySubmissionServiceImpl implements EssaySubmissionService{
    private final EssaySubmissionRepository essaySubmissionRepository;

    @Autowired
    public EssaySubmissionServiceImpl(EssaySubmissionRepository essaySubmissionRepository) {
        this.essaySubmissionRepository = essaySubmissionRepository;
    }

    @Override
    public EssaySubmission save(EssaySubmission submission) {
        return essaySubmissionRepository.save(submission);
    }
}
