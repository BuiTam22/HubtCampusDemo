package com.codecampushubt.NCKH2024TQQD.service.RoleServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecampushubt.NCKH2024TQQD.dao.RoleRepository;
import com.codecampushubt.NCKH2024TQQD.entity.Role;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(int theId) {
        Optional<Role> result = roleRepository.findById(theId);
        return result;
    }

    @Override
    public Role save(Role theRole) {
        return roleRepository.save(theRole);
    }

    @Override
    public void deleteByid(int theId) {
        roleRepository.deleteById(theId);
    }

    @Override
    public List<String> getRoleNameByUserName(String theUserName) {
        return (List<String>) roleRepository.getRoleNameByUserName(theUserName);
    }
}
