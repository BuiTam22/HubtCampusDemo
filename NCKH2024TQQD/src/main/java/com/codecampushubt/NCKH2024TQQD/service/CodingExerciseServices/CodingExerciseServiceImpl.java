package com.codecampushubt.NCKH2024TQQD.service.CodingExerciseServices;

import com.codecampushubt.NCKH2024TQQD.dao.CodingExerciseRepository;
import com.codecampushubt.NCKH2024TQQD.dao.ExerciseTestCaseRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO.CodingExerciseDetailDTO;
import com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO;
import com.codecampushubt.NCKH2024TQQD.entity.CodingExercise;
import com.codecampushubt.NCKH2024TQQD.entity.ExerciseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CodingExerciseServiceImpl implements CodingExerciseService {
    private final CodingExerciseRepository codingExerciseRepository;
    private final ExerciseTestCaseRepository exerciseTestCaseRepository;

    @Autowired
    public CodingExerciseServiceImpl(CodingExerciseRepository codingExerciseRepository, ExerciseTestCaseRepository exerciseTestCaseRepository) {
        this.codingExerciseRepository = codingExerciseRepository;
        this.exerciseTestCaseRepository = exerciseTestCaseRepository;
    }

    @Override
    public List<CodingExerciseDTO> getCodingExerciseDTOByLessonSlug(String theSlug) {
        return codingExerciseRepository.getCodingExerciseDTOByLessonSlug(theSlug);
    }

    @Override
        public CodingExerciseDetailDTO getCodingExerciseDetailDTOByExerciseSlug(String theSlug) {
            CodingExerciseDetailDTO codingExerciseDetailDTO = codingExerciseRepository.getCodingExerciseDetailDTOByExerciseSlug(theSlug);
            Set<ExerciseTestCasesDTO> exerciseTestCases = exerciseTestCaseRepository.getExerciseTestCasesDTOByExerciseID(codingExerciseDetailDTO.getExerciseID());
            codingExerciseDetailDTO.setExerciseTestCases(exerciseTestCases);
            return codingExerciseDetailDTO;
    }

    @Override
    public CodingExercise getExerciseEntityByID(Long exerciseID) {
        return codingExerciseRepository.getExerciseEntityByID(exerciseID);
    }

}
