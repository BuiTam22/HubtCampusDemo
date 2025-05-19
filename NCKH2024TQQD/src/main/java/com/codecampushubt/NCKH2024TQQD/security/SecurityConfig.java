package com.codecampushubt.NCKH2024TQQD.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserContextFilter userContextFilter;

    public SecurityConfig(JwtFilter jwtFilter, UserContextFilter userContextFilter) {
        this.jwtFilter = jwtFilter;
        this.userContextFilter = userContextFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(userContextFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(
                        "/api/user/**",
//                        "/login/show",
                        "/AdminStatic/**", // Cho phép tất cả tài nguyên static AdminStatic
                        "/ClientStatic/**" ,// Cho phép tất cả tài nguyên static ClientStatic
                        "/test-connection",
                        "/api/course/add",
                        "/AdminStatic/**",
                        "/ClientStatic/**",
                        "/api/upload"
         ).permitAll()
                // Cho phép Spring Security xử lý phần chứng thực cơ bản
                // Phần kiểm tra permissions cụ thể sẽ được xử lý trong JwtFilter
                 .anyRequest().permitAll();

        return http.build();
    }
}