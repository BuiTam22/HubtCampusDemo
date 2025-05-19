package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowithRolenameDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // khong can CRUD co ban nua
    @Query("SELECT new com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowDTO(" +
            "c.courseID, c.title, c.slug, c.description, c.shortDescription, " +
            "c.instructor.userName, c.rating, c.price, c.discountPrice, c.imageUrl) " +
            "FROM Course c")
    List<CourseShowDTO> getCourseShowDTO();

    // Hàm này tương đương: SELECT COUNT(*) > 0 FROM courses WHERE slug = :slug
    boolean existsBySlug(String slug);
//lấy userID theo username
    @Query("SELECT u.userID FROM User u WHERE u.userName = :userName")
    Long findUserIdByUserName(@Param("userName") String userName);

//lấy userrole theo username
    @Query("SELECT r.roleName FROM User u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "WHERE u.userName = :userName")
    List<String> findRoleNamesByUserName(@Param("userName") String userName);



    @Query("SELECT new com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowithRolenameDTO(" +
            "c.courseID, c.title, c.slug, c.description, c.shortDescription, " +
            "c.instructor.userName, c.rating, c.price, c.discountPrice, c.imageUrl, r.roleName) " +
            "FROM Course c " +
            "JOIN c.instructor.userRoles ur " +
            "JOIN ur.role r " +
            "WHERE c.instructor.userID = :userId")
    List<CourseShowithRolenameDTO> getCoursesShowDTOByUserId(@Param("userId") Long userId);



    @Query("SELECT new com.codecampushubt.NCKH2024TQQD.dto.CourseDTO.CourseShowithRolenameDTO(" +
            "c.courseID, c.title, c.slug, c.description, c.shortDescription, " +
            "c.instructor.userName, c.rating, c.price, c.discountPrice, c.imageUrl, r.roleName) " +
            "FROM Course c " +
            "JOIN c.instructor.userRoles ur " +
            "JOIN ur.role r")
    List<CourseShowithRolenameDTO> getAllCoursesShowDTOWithRole();

    }










