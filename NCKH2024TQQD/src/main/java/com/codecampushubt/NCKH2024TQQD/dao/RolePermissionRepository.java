package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.entity.Permission;
import com.codecampushubt.NCKH2024TQQD.entity.Role;
import com.codecampushubt.NCKH2024TQQD.entity.RolePermission;
import com.codecampushubt.NCKH2024TQQD.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId > {
    List<RolePermission> findByRole(Role role);
    boolean existsById(RolePermissionId id);
    Optional<RolePermission> findByRoleAndPermission(Role role,Permission permission);
    boolean existsByRoleAndPermission(Role role, Permission permission);
    boolean deleteByRoleAndPermission(Role role, Permission permission);

}
