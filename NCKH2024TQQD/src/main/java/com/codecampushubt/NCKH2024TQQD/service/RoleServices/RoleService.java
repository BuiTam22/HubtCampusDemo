package com.codecampushubt.NCKH2024TQQD.service.RoleServices;


import java.util.List;
import java.util.Optional;

import com.codecampushubt.NCKH2024TQQD.entity.Role;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(int theId);
    Role save(Role theRole);
    void deleteByid(int theId);
    List<String> getRoleNameByUserName(String theUserName);
}
