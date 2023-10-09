package com.example.demo.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Response {
	
	Object object;
	String message;

}
