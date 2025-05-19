package com.codecampushubt.NCKH2024TQQD.service.LessonServices;

import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.*;
import com.codecampushubt.NCKH2024TQQD.entity.CourseLesson;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonService {
    List<LessonShowDTO> getLessonShowDTO(Long theID);
    List<LessonShowDTO> getLessonShowDTOByModuleIDAndSlug(Long moduleID, String search);
    List<ContestShowDTO> getContestShowDTOByIsContest(Long moduleID);
    List<ContestShowDTO> getEssayContestShowDTOByIsContest(Long moduleID);
    List<LessonShowDTOA> getLessonShowDTOA();
    CourseLesson addLesson(CreateLessonsDTO dto);
    List<ContestManagementShowDTO> getContestManagementShowDTO(Long moduleID, String userName);
    CourseLesson save(CourseLesson theLesson);
    EditLessonDTO getEditLessonDTO(Long moduleID, String theSlug);
}
