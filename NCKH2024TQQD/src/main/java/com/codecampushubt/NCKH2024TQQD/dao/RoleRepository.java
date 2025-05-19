package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Tìm role theo một tên role duy nhất
    Optional<Role> findByRoleName(String roleName);

    // Tìm nhiều role theo danh sách tên role
    @Query("SELECT r FROM Role r WHERE r.roleName IN :roleNames")
    List<Role> findByRoleNames(@Param("roleNames") List<String> roleNames);

    // Lấy danh sách roleName của user theo userName
    @Query("SELECT DISTINCT r.roleName " +
            "FROM User u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "WHERE u.userName = :userName")
    List<String> getRoleNameByUserName(@Param("userName") String userName);




}

