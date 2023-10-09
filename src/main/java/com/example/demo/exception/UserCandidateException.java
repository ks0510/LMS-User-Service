package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCandidateException extends RuntimeException{
	
	String message;
	int statusCode;

}
