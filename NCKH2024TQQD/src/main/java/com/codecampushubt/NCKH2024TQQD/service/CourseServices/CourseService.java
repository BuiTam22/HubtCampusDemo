package com.codecampushubt.NCKH2024TQQD.service.CourseServices;

import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseModuleDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowithRolenameDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(long theId);
    Course save(Course theCourse);
    void deleteByid(long theId);
    List<CourseShowDTO> getCourseShowDTO();
    List<CourseModuleDTO> getCourseModuleByCourseSlug(String theSlug);
    String generateUniqueSlug(String baseSlug);
    List<CourseShowithRolenameDTO> getAllCoursesBasedOnUserRole();
}
