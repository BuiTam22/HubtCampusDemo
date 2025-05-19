package com.codecampushubt.NCKH2024TQQD.controller.Admin.Course;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowithRolenameDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import com.codecampushubt.NCKH2024TQQD.service.CourseServices.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/course")
public class courseApiController {
    private final CourseService courseService;
    public courseApiController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<CourseShowithRolenameDTO>> showCoursesBasedOnRole(){
        List<CourseShowithRolenameDTO> course = courseService.getAllCoursesBasedOnUserRole();
        return ResponseEntity.ok(course);
    }

    }

