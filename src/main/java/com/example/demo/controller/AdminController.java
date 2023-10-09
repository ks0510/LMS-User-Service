package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Response;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@GetMapping("/getuserbyemail/{email}/{token}")
	public ResponseEntity<Response> getUserByEmail(@PathVariable String email,@PathVariable String token) {
		return userService.findByEmail(email,token);
	}

	@GetMapping("/getuserbyphonenumber/{phoneNumber}/{token}")
	public ResponseEntity<Response> getUserByPhoneNumber(@PathVariable String phoneNumber,@PathVariable String token) {
		return userService.findByPhoneNumber(phoneNumber,token);
	}

	@GetMapping("/getuserbyid/{id}/{token}")
	public ResponseEntity<Response> getUserId(@PathVariable int id,@PathVariable String token) {
		return userService.findById(id,token);
	}

}
