package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.dao.UserRepository;
import com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginBasicDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserBasicInfoDTO;
import com.codecampushubt.NCKH2024TQQD.entity.*;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/api/user")
@RestController()
public class RestUser {
    private final UserService userService;

    @Autowired
    public RestUser(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/hello")
//    public ArrayList<User> hello() {
//
//        return (ArrayList<User>) entityManager.createQuery(("SELECT u FROM User u WHERE u.id = 1", User.class).getResultList();
//    }

//    @GetMapping("/hello1")
//    public ArrayList<User> hello1() {
//
//        return (ArrayList<User>) userRepository.findByEmailAndUserName("tam22032004@gmail.com", "test1");
//    }

    @GetMapping("/basic-info/{id}")
    public ArrayList<UserBasicInfoDTO> getUserBasicInfo(@PathVariable("id") Long theID){
        return userService.getUserBasicInfo(theID);
    }

    @GetMapping("/find-all")
    public ArrayList<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/fullName")
    public String getFullName(String userName){
        return userService.getFullName("tambui");
    }

    @GetMapping("/login-info/{userName}")
    public LoginBasicDTO getLoginBasicDTO(@PathVariable("userName") String userName){
        return userService.getLoginBasicDTO(userName);
    }
}
