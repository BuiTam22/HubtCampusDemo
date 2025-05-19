package com.codecampushubt.NCKH2024TQQD.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordUtil {

    public boolean passwordMatches(String rawPassword, String hashedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }
}
