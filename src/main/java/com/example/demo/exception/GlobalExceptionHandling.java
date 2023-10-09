package com.example.demo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Response;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandling {
	
	@Autowired
	Response response;

	@ExceptionHandler(value = UserCandidateException.class)
	public ResponseEntity<Response> findException(UserCandidateException exception) {
		response.setObject(null);
		response.setMessage(exception.message);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
