package com.codecampushubt.NCKH2024TQQD.service.CourseServices;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dao.CourseModuleRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseModuleDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowithRolenameDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import com.codecampushubt.NCKH2024TQQD.dao.CourseRepository;
import com.github.slugify.Slugify;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseModuleRepository courseModuleRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository theCourseRepository, CourseModuleRepository courseModuleRepository) {
        this.courseRepository = theCourseRepository;
        this.courseModuleRepository = courseModuleRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(long theId) {
        Optional<Course> result = courseRepository.findById(theId);
        return result.orElseThrow(() -> new RuntimeException("Course not found with id = " + theId));
    }

    @Override
    @Transactional
    public Course save(Course theCourse) {
        String baseSlug = new Slugify().slugify(theCourse.getTitle());
        String uniqueSlug = generateUniqueSlug(baseSlug);
        theCourse.setSlug(uniqueSlug);
        return courseRepository.save(theCourse);
    }

    @Override
    @Transactional
    public void deleteByid(long theId) {
        courseRepository.deleteById((long) theId);
    }

    @Override
    public List<CourseShowDTO> getCourseShowDTO() {
        return courseRepository.getCourseShowDTO();
    }

    @Override
    public List<CourseModuleDTO> getCourseModuleByCourseSlug(String theSlug) {
        return courseModuleRepository.getCourseModuleByCourseSlug(theSlug);
    }

    @Override
    public String generateUniqueSlug(String baseSlug) {
        String slug = baseSlug;
        int counter = 1;
        while (courseRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter;
            counter++;
        }
        return slug;
    }
    @Override
    public List<CourseShowithRolenameDTO> getAllCoursesBasedOnUserRole() {
        String UserName = UserContext.getUsername();
        List<String> roleName = courseRepository.findRoleNamesByUserName(UserName);
//        return courseRepository.getAllCourseShowDTO();
        if (roleName.contains("ADMIN")){
            System.out.println("Admin");
            return courseRepository.getAllCoursesShowDTOWithRole();
        }
        Long userId = courseRepository.findUserIdByUserName(UserName);
        System.out.println("userId = " + userId);

        if (userId != null){
            return courseRepository.getCoursesShowDTOByUserId(userId);}
        else {
            return new ArrayList<>();
        }
    }
}
