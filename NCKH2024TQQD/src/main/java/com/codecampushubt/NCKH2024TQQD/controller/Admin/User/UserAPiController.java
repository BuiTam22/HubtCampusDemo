package com.codecampushubt.NCKH2024TQQD.controller.Admin.User;

import com.codecampushubt.NCKH2024TQQD.dao.UserRepository;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserCreateDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserShowDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserUpdateDTO;
import com.codecampushubt.NCKH2024TQQD.entity.User;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/user")
public class UserAPiController {
    private final UserService userService;


    public UserAPiController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/show")
    public ResponseEntity<List<UserShowDTO>> getAllUsers() {
        List<UserShowDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO dto) {
//        System.out.println(user);
        try {
            userService.addUser(dto); // Gọi phương thức thêm người dùng từ UserService
//            System.out.println(dto);
            return ResponseEntity.ok("Thêm người dùngssss thành công!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Trả lỗi nếu có vấn đề
        }
    }
    @PutMapping("/update/{userID}")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO dto, @PathVariable long userID) {
        try {
//            System.out.println(dto);
//            System.out.println(userID);
//            System.out.println("đã vào controller");
//            System.out.println(dto);
            // Gọi service để cập nhật người dùng
            userService.updateUser(userID, dto);
            return ResponseEntity.ok("Cập nhật người dùng thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }

    @GetMapping("/showUpdate/{userID}")
    public ResponseEntity<UserUpdateDTO> getUserUpdateDTOById(@PathVariable Long userID) {
        UserUpdateDTO dto = userService.getUserUpdateDTOById(userID);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        System.out.println(userId);
        User deleteUser = userService.softDeleteUser(userId);
        return ResponseEntity.ok("deleteUser");
    }

}
