package com.codecampushubt.NCKH2024TQQD.service.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginBasicDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserBasicInfoDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserCreateDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserUpdateDTO;
import com.codecampushubt.NCKH2024TQQD.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

public interface UserService {
    ArrayList<UserBasicInfoDTO> getUserBasicInfo(Long userID);

    ArrayList<User> findAll();

    LoginBasicDTO getLoginBasicDTO(String userName);

    List<UserShowDTO> getAllUsers();

    User addUser(UserCreateDTO dto);

    UserUpdateDTO  getUserUpdateDTOById(Long userID);


    User updateUser(Long userId, UserUpdateDTO dto);

    String getFullName(String userName);

    Long findUserIDByUserName(String userName);

    User getUserEntityByID(Long userID);

//    List<User> findAllActiveUsers();
    Page<UserShowDTO> getAllUsers(int page, int size);

    User softDeleteUser(Long userID);

    Optional<User> findByUserName(String userName);


}
