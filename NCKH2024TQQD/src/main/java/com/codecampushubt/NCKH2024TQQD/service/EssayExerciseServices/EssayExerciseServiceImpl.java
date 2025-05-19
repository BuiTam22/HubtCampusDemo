package com.codecampushubt.NCKH2024TQQD.service.EssayExerciseServices;

import com.codecampushubt.NCKH2024TQQD.dao.EssayExerciseRepository;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseDetailShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO.EssayExerciseListShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayExerciseServiceImpl implements EssayExerciseService{
    private final EssayExerciseRepository essayExerciseRepository;

    @Autowired
    public EssayExerciseServiceImpl(EssayExerciseRepository essayExerciseRepository) {
        this.essayExerciseRepository = essayExerciseRepository;
    }

    @Override
    public List<EssayExerciseListShowDTO> getEssayExerciseListShowDTOByLessonSlug(String theSlug) {
        return essayExerciseRepository.getEssayExerciseListShowDTOByLessonSlug(theSlug);
    }

    @Override
    public EssayExerciseDetailShowDTO getEssayExerciseDetailShowDTOBySlug(String theSlug) {
        return essayExerciseRepository.getEssayExerciseDetailShowDTOBySlug(theSlug);
    }

    @Override
    public String getExpectedAnswerOfEssayExerciseByExerciseID(Long theID) {
        return essayExerciseRepository.getExpectedAnswerOfEssayExerciseByExerciseID(theID);
    }
}
