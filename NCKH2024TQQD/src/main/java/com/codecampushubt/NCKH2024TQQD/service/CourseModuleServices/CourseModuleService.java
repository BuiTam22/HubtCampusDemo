package com.codecampushubt.NCKH2024TQQD.service.CourseModuleServices;

import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseModuleDTO;
import com.codecampushubt.NCKH2024TQQD.entity.CourseModule;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseModuleService {
    List<CourseModuleDTO> getCourseModuleByCourseSlug(String theSlug);

    Optional<CourseModule> findBySlug(String slug);

    Optional<CourseModule> findByModuleID(Long moduleID);

    boolean existsBySlug(String slug);
}
