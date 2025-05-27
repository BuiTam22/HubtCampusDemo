package com.codecampushubt.NCKH2024TQQD.service.LessonServices;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dao.CourseModuleRepository;
import com.codecampushubt.NCKH2024TQQD.dao.CourseRepository;
import com.codecampushubt.NCKH2024TQQD.dao.LessonRepository;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.*;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import com.codecampushubt.NCKH2024TQQD.entity.CourseLesson;
import com.codecampushubt.NCKH2024TQQD.entity.CourseModule;
import com.github.slugify.Slugify;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;
    private final CourseModuleRepository courseModuleRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository ,CourseModuleRepository courseModuleRepository ) {
        this.lessonRepository = lessonRepository;
        this.courseModuleRepository = courseModuleRepository;
    }

    @Override
    public List<LessonShowDTO> getLessonShowDTO(Long theID) {
        return lessonRepository.getLessonShowDTO(theID);
    }

    @Override
    public List<LessonShowDTO> getLessonShowDTOByModuleIDAndSlug(Long moduleID, String search) {
        String processed = search.replace("++", "-plus-plus");
        String newSlug = new Slugify().slugify(processed);
        return lessonRepository.getLessonShowDTOByModuleIDAndSlug(moduleID, newSlug);
    }

    @Override
    public List<ContestShowDTO> getContestShowDTOByIsContest(Long moduleID) {
        return lessonRepository.getContestShowDTOByIsContest(moduleID);
    }

    @Override
    public List<ContestShowDTO> getEssayContestShowDTOByIsContest(Long moduleID) {
        return lessonRepository.getEssayContestShowDTOByIsContest(3L);
    }

    @Override
    @Transactional
    public CourseLesson save(CourseLesson theLesson){
        String baseSlug = new Slugify().slugify(theLesson.getTitle());
        String uniqueSlug = generateUniqueSlug(baseSlug);
        theLesson.setSlug(uniqueSlug);
        return lessonRepository.save(theLesson);
    }

    @Override
    public EditLessonDTO getEditLessonDTO(Long moduleID, String theSlug) {
        return lessonRepository.getEditLessonDTO(moduleID, theSlug);
    }

    @Override
    public Optional<CourseLesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    public String generateUniqueSlug(String baseSlug) {
        String slug = baseSlug;
        int counter = 1;
        while (lessonRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter;
            counter++;
        }
        return slug;
    }

    @Override
    public List<LessonShowDTOA> getLessonShowDTOA(){
        String userName = UserContext.getUsername();
        List<String> roleName = lessonRepository.findRoleNameByUserName(userName);
        Long UserID = lessonRepository.findUserIdByUsername(userName);
        if (roleName.contains("ADMIN")){
            return lessonRepository.findLessonByRoleName("ADMIN");

        }else {
            return lessonRepository.findLessonByUserID(UserID);
        }



    }

    @Override
    public CourseLesson addLesson(CreateLessonsDTO dto ){
//        System.out.println(dto);
        Slugify slugify = new Slugify();
        String Slug = slugify.slugify(dto.getTitle());
//        System.out.println(dto.getCourseName());
        String courseName = dto.getCourseName();
        CourseModule module = courseModuleRepository.findBySlug(courseName)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy module"));
//        System.out.println(module);
//        khởi tạo
        CourseLesson courseLesson = new CourseLesson();
        courseLesson.setModule(module);
        courseLesson.setTitle(dto.getTitle());
        courseLesson.setDescription(dto.getDescription());
        courseLesson.setType(dto.getType());
        courseLesson.setContent(dto.getContent());
        courseLesson.setImage(dto.getImage());
        courseLesson.setDuration(dto.getDuration());
        courseLesson.setSlug(Slug);
        courseLesson.setOrderIndex(dto.getOrderIndex());

        lessonRepository.save(courseLesson);

        return courseLesson;
    }

    @Override
    public List<ContestManagementShowDTO> getContestManagementShowDTO(Long moduleID, String userName) {
        return lessonRepository.getContestManagementShowDTO(moduleID, userName);
    }

}
