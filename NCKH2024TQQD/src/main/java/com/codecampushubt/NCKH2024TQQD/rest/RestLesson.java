package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.*;
import com.codecampushubt.NCKH2024TQQD.entity.CourseLesson;
import com.codecampushubt.NCKH2024TQQD.entity.CourseModule;
import com.codecampushubt.NCKH2024TQQD.entity.User;
import com.codecampushubt.NCKH2024TQQD.service.CourseModuleServices.CourseModuleService;
import com.codecampushubt.NCKH2024TQQD.service.LessonServices.LessonService;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/lesson")
public class RestLesson {
    private final LessonService lessonService;
    private final UserService userService;
    private final CourseModuleService courseModuleService;

    @Autowired
    public RestLesson(LessonService lessonService, UserService userService, CourseModuleService courseModuleService) {
        this.lessonService = lessonService;
        this.userService = userService;
        this.courseModuleService = courseModuleService;
    }

    @GetMapping("/find-by-id/{id}")
    public List<LessonShowDTO> getLessonShowDTO(@PathVariable("id") Long theID){
        return lessonService.getLessonShowDTO(theID);
    }

    @GetMapping("/contest/{moduleID}")
    private List<ContestShowDTO> getLessonShowDTOByIsContest(@PathVariable("moduleID") Long moduleID){
        return lessonService.getContestShowDTOByIsContest(moduleID);
    }

    @GetMapping("/show-lesson-contest/{moduleID}")
    List<ContestManagementShowDTO> getContestManagementShowDTO(@PathVariable("moduleID") Long moduleID, String userName){
        userName = UserContext.getUsername();
        return lessonService.getContestManagementShowDTO(moduleID, userName);
    }

    @GetMapping("/show-lesson-essay-contest/{moduleID}")
    List<ContestShowDTO> getEssayContestShowDTOByIsContest(@PathVariable("moduleID") Long moduleID, String userName){
        return lessonService.getEssayContestShowDTOByIsContest(moduleID);
    }

    @PostMapping("/contest/create")
    public ResponseEntity<?> save(@RequestBody() CreateLessonClientDTO theLesson) {
        try {
            // Tạo đối tượng Lesson từ DTO
            CourseLesson lesson = new CourseLesson();
            lesson.setTitle(theLesson.getTitle());
            lesson.setDuration(theLesson.getDuration());
            lesson.setType(theLesson.getType());
            lesson.setIsContest(theLesson.getIsContest());
            lesson.setContestStartTime(theLesson.getContestStartTime());
            lesson.setContestEndTime(theLesson.getContestEndTime());

            // Lấy thông tin người dùng và module (nếu có)
            User user = userService.findByUserName(UserContext.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            lesson.setCreator(user);

            CourseModule module = courseModuleService.findByModuleID(3L)
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            lesson.setModule(module);

            // Lưu lesson vào cơ sở dữ liệu
            lesson = lessonService.save(lesson);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", new LessonResponseDTO(lesson)); // hoặc chỉ id, title,...
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Trả về ResponseEntity với mã trạng thái 400 (Bad Request) nếu có lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating lesson: " + e.getMessage());
        }
    }


}

