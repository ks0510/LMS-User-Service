package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
@EnableEurekaClient
public class LmsUserApplication {
	
	@Bean
	public SimpleMailMessage getSimpleMailMessage() {
		return new SimpleMailMessage();
	}

	public static void main(String[] args) {
		SpringApplication.run(LmsUserApplication.class, args);
	}

}
