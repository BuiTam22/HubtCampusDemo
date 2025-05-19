package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.CourseModule.CourseModuleFILLDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.*;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import com.codecampushubt.NCKH2024TQQD.entity.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<CourseLesson, Long> {
    // lấy ra lesson luyện tập
    @Query("""
        SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.LessonShowDTO(
        cl.lessonID, cl.module.id, cl.title, cl.description, cl.type, cl.content,
        cl.image, cl.duration, cl.orderIndex, cl.isPreview, cl.isPublished, cl.slug)
        FROM CourseLesson cl
        WHERE cl.module.id = :moduleID AND cl.isContest = false
    """)
    List<LessonShowDTO> getLessonShowDTO(@Param("moduleID") Long moduleID);

    // tìm kiếm lesson theo slug
    @Query("""
        SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.LessonShowDTO(
        cl.lessonID, cl.module.id, cl.title, cl.description, cl.type, cl.content,
        cl.image, cl.duration, cl.orderIndex, cl.isPreview, cl.isPublished, cl.slug)
        FROM CourseLesson cl
        WHERE cl.module.id = :moduleID AND cl.slug LIKE %:slug% AND cl.isContest = false
    """)
    List<LessonShowDTO> getLessonShowDTOByModuleIDAndSlug(@Param("moduleID") Long moduleID, @Param("slug") String theSlug);

    //Lấy ra những lesson là contest và có type = coding
    @Query("""
        SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.ContestShowDTO(
        cl.lessonID, cl.title, cl.description, cl.type,
        cl.duration, cl.image, cl.isPreview, cl.slug, cl.contestStartTime, cl.contestEndTime)
        FROM CourseLesson cl
        WHERE cl.module.id = :moduleID AND cl.isContest = true AND CURRENT_TIMESTAMP < cl.contestEndTime AND cl.type = "coding"
    """)
    List<ContestShowDTO> getContestShowDTOByIsContest(@Param("moduleID") Long moduleID);

    //Lấy ra những lesson là contest và có type = essay
    @Query("""
        SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.ContestShowDTO(
        cl.lessonID, cl.title, cl.description, cl.type,
        cl.duration, cl.image, cl.isPreview, cl.slug, cl.contestStartTime, cl.contestEndTime)
        FROM CourseLesson cl
        WHERE cl.module.id = :moduleID AND cl.isContest = true AND CURRENT_TIMESTAMP < cl.contestEndTime AND cl.type = "essay"
    """)
    List<ContestShowDTO> getEssayContestShowDTOByIsContest(@Param("moduleID") Long moduleID);

    //Lấy ra những lesson/contest với username
    @Query("""
        SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.ContestManagementShowDTO(
        cl.title, cl.slug, cl.creator.userName, cl.contestStartTime, cl.contestEndTime)
        FROM CourseLesson cl
        WHERE cl.module.id = :moduleID AND cl.creator.userName = :userName
    """)
    List<ContestManagementShowDTO> getContestManagementShowDTO(@Param("moduleID") Long moduleID, @Param("userName") String userName);

    // lấy ra các trường có thể chỉnh sửa của lesson theo slug
    @Query("""
        SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.EditLessonDTO(
        cl.lessonID, cl.title, cl.description, cl.image, cl.duration, cl.type,
        cl.isContest, cl.contestStartTime, cl.contestEndTime, cl.slug)
        FROM CourseLesson cl
        WHERE cl.module.id = :moduleID AND cl.slug = :theSlug
    """)
    EditLessonDTO getEditLessonDTO(@Param("moduleID") Long moduleID, @Param("theSlug") String theSlug);

    // Hàm này tương đương: SELECT COUNT(*) > 0 FROM courselesson WHERE slug = :slug
    boolean existsBySlug(String slug);

    @Query("SELECT r.roleName FROM User u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "WHERE u.userName = :userName ")
    List<String> findRoleNameByUserName(String userName);


    @Query("SELECT u.userID FROM User u WHERE u.userName = :username")
    Long findUserIdByUsername(@Param("username") String username);



    //    @Query(value = "SELECT CONCAT(c.title, ' - ', c.slug) FROM courses c JOIN [Users] u ON c.instructorID = u.userID WHERE u.userName = :username", nativeQuery = true)
//    List<String> findCourseNamesByInstructorUsername(@Param("username") String username);
    @Query("select new com.codecampushubt.NCKH2024TQQD.dto.CourseModule.CourseModuleFILLDTO(" +
            "cm.title , cm.slug) " +
            "from CourseModule cm " +
            "join cm.course c " +
            "join c.instructor u " +
            "where u.userName = :userName")
    List<CourseModuleFILLDTO> findModulesByInstructorUserName(@Param("userName") String userName);


    @Query("SELECT new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.LessonShowDTOA(" +
            "cl.lessonID , cm.title, cl.title , cl.description , cl.type , cl.content , cl.duration , r.roleName , u.userName) " +
            "FROM CourseLesson cl " +
            "JOIN cl.module cm " +
            "JOIN cm.course c " +
            "JOIN c.instructor u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "WHERE r.roleName = :roleName")
    List<LessonShowDTOA> findLessonByRoleName(@Param("roleName") String roleName);

    @Query("select new com.codecampushubt.NCKH2024TQQD.dto.LessonDTO.LessonShowDTOA(" +
            "cl.lessonID , cm.title , cl.title , cl.description , cl.type ,cl.content , cl.duration , r.roleName , u.userName )" +
            "FROM CourseLesson cl " +
            "join cl.module cm " +
            "join cm.course c " +
            "join c.instructor u " +
            "join u.userRoles ur " +
            "join ur.role r " +
            "where u.userID = :userID")
    List<LessonShowDTOA> findLessonByUserID(@Param("userID") Long userID);


}
