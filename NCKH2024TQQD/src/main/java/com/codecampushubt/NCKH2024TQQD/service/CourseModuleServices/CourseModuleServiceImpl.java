package com.codecampushubt.NCKH2024TQQD.service.CourseModuleServices;

import com.codecampushubt.NCKH2024TQQD.dao.CourseModuleRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseModuleDTO;
import com.codecampushubt.NCKH2024TQQD.entity.CourseModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseModuleServiceImpl implements CourseModuleService{
    private final CourseModuleRepository courseModuleRepository;

    @Autowired
    public CourseModuleServiceImpl(CourseModuleRepository courseModuleRepository) {
        this.courseModuleRepository = courseModuleRepository;
    }

    @Override
    public List<CourseModuleDTO> getCourseModuleByCourseSlug(String theSlug) {
        return courseModuleRepository.getCourseModuleByCourseSlug(theSlug);
    }

    @Override
    public Optional<CourseModule> findBySlug(String slug) {
        return courseModuleRepository.findBySlug(slug);
    }

    @Override
    public Optional<CourseModule> findByModuleID(Long id) {
        return courseModuleRepository.findByModuleID(id);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return courseModuleRepository.existsBySlug(slug);
    }
}
