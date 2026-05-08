package com.achan.ai_learning_profile_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.achan/ai_learning_profile_backend.mapper")
@SpringBootApplication
public class AiLearningProfileBackendApplication {

	public static void main(String[] args) {
        SpringApplication.run(AiLearningProfileBackendApplication.class, args);
	}

}
