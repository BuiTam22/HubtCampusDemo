package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionNameDTO;
import com.codecampushubt.NCKH2024TQQD.service.PermissionServices.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class RestPermission {
    private PermissionService permissionService;

    @Autowired
    public RestPermission(PermissionService permissionService){
        this.permissionService=permissionService;
    }

    @GetMapping("/user-permission")
    public List<String> getPermissionName(){
        return permissionService.getPermissionNameDTO("test1");
    }
}
