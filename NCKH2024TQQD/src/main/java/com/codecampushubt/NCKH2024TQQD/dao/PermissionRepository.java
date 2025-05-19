package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionAssignDTO;
import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionNameDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    // lấy toàn bộ permission của user thông qua userName
    @Query("SELECT DISTINCT p.permissionName " +
            "FROM User u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "JOIN r.rolePermissions rp " +
            "JOIN rp.permission p " +
            "WHERE u.userName = :userName")
    List<String> getPermissionNameDTO(@Param("userName") String userName);

    Optional<Permission> findByPermissionName(String permissionName);
//    PermissionAssignDTO updateBy(Pẻ)




}
