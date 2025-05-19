package com.codecampushubt.NCKH2024TQQD.rest;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.ForgotPasswordRequest;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.ResetPasswordRequest;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.UserCreateDTO;
import com.codecampushubt.NCKH2024TQQD.dto.UserDTO.veriOtp;
import com.codecampushubt.NCKH2024TQQD.service.PermissionServices.PermissionService;
import com.codecampushubt.NCKH2024TQQD.service.RoleServices.RoleService;
import com.codecampushubt.NCKH2024TQQD.service.PassResetServices.PassResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginBasicDTO;
import com.codecampushubt.NCKH2024TQQD.dto.LoginDTO.LoginRequestDTO;
import com.codecampushubt.NCKH2024TQQD.service.JWTServices.JwtService;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import com.codecampushubt.NCKH2024TQQD.util.BCryptPasswordUtil;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class RestLogin {

    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordUtil bCryptPasswordUtil;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final PassResetService PassResetService;


    @Autowired
    public RestLogin(UserService userService, JwtService jwtService, BCryptPasswordUtil bCryptPasswordUtil1, PermissionService permissionService, RoleService roleService
    ,
                     PassResetService PassResetService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.bCryptPasswordUtil = bCryptPasswordUtil1;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.PassResetService = PassResetService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request, HttpServletResponse response, CsrfToken csrfToken) {

        LoginBasicDTO user = userService.getLoginBasicDTO(request.getUsername());

        if (user == null || !bCryptPasswordUtil.passwordMatches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUserName(), permissionService.getPermissionNameDTO(user.getUserName()), roleService.getRoleNameByUserName(user.getUserName()), user.getUserId());
        // Tạo cookie chứa JWT
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false) // Đặt true nếu dùng HTTPS
                .path("/")
                .maxAge(24 * 60 * 60) // 1 ngày
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Login successful" + cookie.toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Xóa cookie bằng cách đặt maxAge = 0
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0) // Xóa ngay lập tức
                .build();

        response.setHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateDTO dto){

        try {
            userService.addUser(dto);
            System.out.println(dto);
//            System.out.println(dto);
            return ResponseEntity.ok("Thêm người dùngssss thành công!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Trả lỗi nếu có vấn đề
        }
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> sendOtp (@RequestBody ForgotPasswordRequest dto ){
        PassResetService.sendOtpToEmail(dto.getEmail());
        return ResponseEntity.ok("OTP Đã Được Gửi Về Email .");
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp (@RequestBody veriOtp dto) {
        String very = PassResetService.verifyOtp(dto.getEmail(), dto.getOtp());
        System.out.println(very);
        return ResponseEntity.ok(Map.of("message", very));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest dto) {
        String message = PassResetService.resetPassword(dto.getEmail(), dto.getOtp(), dto.getNewPassword());
        System.out.println(dto);
        return ResponseEntity.ok(Map.of("message", message));
    }


}
