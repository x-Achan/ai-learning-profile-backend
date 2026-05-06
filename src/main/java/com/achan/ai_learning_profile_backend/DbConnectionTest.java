package com.achan.ai_learning_profile_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DbConnectionTest {

    @Bean
    public CommandLineRunner testDbConnection(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("MySQL 连接成功！");
                System.out.println("当前数据库：" + connection.getCatalog());
            }
        };
    }
}