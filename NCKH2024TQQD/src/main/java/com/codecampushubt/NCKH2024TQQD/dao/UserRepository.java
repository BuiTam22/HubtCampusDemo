package com.codecampushubt.NCKH2024TQQD.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginBasicDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserBasicInfoDTO;
import com.codecampushubt.NCKH2024TQQD.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    ArrayList<User> findByEmailAndUserName(String email, String userName);

    @Query("SELECT new com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserBasicInfoDTO(u.userName, u.fullName, u.image) FROM User u WHERE u.userID = :userID")
    ArrayList<UserBasicInfoDTO> getUserBasicInfo(@Param("userID") Long userID);

    // truy vấn 3 trường để so sánh login
    @Query("SELECT new com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginBasicDTO(u.userName, u.email, u.password, u.userID) FROM User u WHERE u.userName = :userName")
    LoginBasicDTO getLoginBasicDTO(@Param("userName") String userName);


    boolean existsByEmail(String email);

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);


    @Query("SELECT DISTINCT u.fullName FROM User u WHERE u.userName = :userName")
    String getFullName(@Param("userName") String userName);

    @Query("SELECT u.userID FROM User u WHERE u.userName = :userName")
    Long getUserIDByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.userID = :userID")
    User getUserEntityByID(@Param("userID") Long userID);

    // Lấy tất cả người dùng có trạng thái ACTIVE
    @Query("SELECT u FROM User u WHERE u.accountStatus = 'ACTIVE'")
    List<User> findAllActiveUsers();

    // Tìm người dùng có trạng thái ACTIVE theo id
    @Query("SELECT u FROM User u WHERE u.userID = :userId AND u.accountStatus = 'ACTIVE'")
    Optional<User> findActiveUserById(Long userId);

    Page<User> findAllByAccountStatusNot(String status, Pageable pageable);







}
