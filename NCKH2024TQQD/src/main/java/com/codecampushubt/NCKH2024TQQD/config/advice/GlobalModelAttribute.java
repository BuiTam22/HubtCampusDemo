package com.codecampushubt.NCKH2024TQQD.config.advice;

import com.codecampushubt.NCKH2024TQQD.service.JWTServices.JwtService;
import com.codecampushubt.NCKH2024TQQD.service.UserServices.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttribute {

    @Value("${base.url}")
    private String baseUrl;


    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public GlobalModelAttribute(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpServletRequest request) {
        // 1. Lấy token từ cookie
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 2. Nếu token hợp lệ, trích xuất username và tìm fullname
        if (token != null && jwtService.validateToken(token)) {
            String username = jwtService.extractUsername(token);
            String fullname = userService.getFullName(username);
            model.addAttribute("userFullName", fullname);
            model.addAttribute("baseUrl", baseUrl);
        }
    }
}
