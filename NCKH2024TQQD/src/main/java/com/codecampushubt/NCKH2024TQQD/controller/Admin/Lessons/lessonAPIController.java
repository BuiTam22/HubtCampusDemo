package com.codecampushubt.NCKH2024TQQD.controller.Admin.Lessons;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dao.LessonRepository;
import com.codecampushubt.NCKH2024TQQD.dto.CourseModule.CourseModuleFILLDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.CreateLessonsDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.LessonShowDTOA;
import com.codecampushubt.NCKH2024TQQD.service.LessonServices.LessonService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController

@RequestMapping("/admin/api/lesson")

public class lessonAPIController {
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;
    @Lazy
    public lessonAPIController(LessonService lessonService, LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
    }
    @GetMapping("/show")
    public ResponseEntity<List<LessonShowDTOA>> showLessons() {
        List<LessonShowDTOA> showlesson = lessonService.getLessonShowDTOA();
        return ResponseEntity.ok(showlesson);
    }
    @GetMapping("/add")
    public ResponseEntity<List<CourseModuleFILLDTO>> addLesson() {
        String username = UserContext.getUsername();
        List<CourseModuleFILLDTO> courseName = lessonRepository.findModulesByInstructorUserName(username);
        return ResponseEntity.ok(courseName);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLesson(@RequestBody CreateLessonsDTO createLessonsDTO) {
        System.out.println(createLessonsDTO);
        lessonService.addLesson(createLessonsDTO);
        return ResponseEntity.ok("Thêm Thành Công ");
    }


//    @PostMapping("/add")
//    public ResponseEntity<String> addLesson(@RequestBody CreateLessonsDTO createLessonsDTO) {
//        return ResponseEntity.ok(createLessonsDTO.toString());
//    }
}
