package com.codecampushubt.NCKH2024TQQD.security;

import com.codecampushubt.NCKH2024TQQD.context.UserContext;
import com.codecampushubt.NCKH2024TQQD.service.JWTServices.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserContextFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Autowired
    public UserContextFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = null;

            // Lấy token từ cookie
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }

            // Nếu token hợp lệ → set vào ThreadLocal
            if (token != null && jwtService.validateToken(token)) {
                String username = jwtService.extractUsername(token);
                Long userID = jwtService.extractUserID(token);
                UserContext.setUsername(username);
                UserContext.setUserID(userID);
            }

            filterChain.doFilter(request, response);
        } finally {
            // Đảm bảo clear sau mỗi request để tránh leak data
            UserContext.clear();
        }
    }
}
