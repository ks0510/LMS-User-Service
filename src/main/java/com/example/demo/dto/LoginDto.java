package com.example.demo.dto;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class LoginDto {
	
	private String email;
	private String password;

}
