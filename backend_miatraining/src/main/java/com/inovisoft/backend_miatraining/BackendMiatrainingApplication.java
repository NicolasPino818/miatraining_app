package com.inovisoft.backend_miatraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendMiatrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMiatrainingApplication.class, args);
	}
}
