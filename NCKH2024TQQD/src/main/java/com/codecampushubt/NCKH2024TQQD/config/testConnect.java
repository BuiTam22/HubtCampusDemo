package com.codecampushubt.NCKH2024TQQD.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
@RestController
public class testConnect {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-connection")
    public String testConnection() {
        try (Connection conn = dataSource.getConnection()) {
            return "✅ Kết nối thành công tới: " + conn.getMetaData().getURL();
        } catch (Exception e) {
            return "❌ Lỗi kết nối: " + e.getMessage();
        }
    }

    }
