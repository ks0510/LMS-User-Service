package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Response;
import com.example.demo.model.UserCandidate;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/usercandidate")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public ResponseEntity<Response> addUser(@RequestBody UserCandidate user) {
		return userService.addUser(user);

	}
	
	@GetMapping("/login")
	public String login(@RequestParam String email,@RequestParam String password) {
		return userService.userLogin(email, password);
	}
	
	
	@GetMapping("/accountverfication")
	public String verifyAccount(@RequestParam String email,@RequestParam String otp) {
		return userService.accountVerification(email, otp);
		
	}
	
	@GetMapping("/regenrateotp")
	public String regenrateOtp(@RequestParam String email,@RequestParam String password ) {
		return userService.regenrateOtp(email, password);
	}
	
	@GetMapping("/forgotpassword")
	public String forgot(@RequestParam String email) {
		return userService.forgotPassword(email);
	}
	
	@GetMapping("/setpassword")
	public String setPassword(@RequestParam String otp,@RequestParam String password) {
		return userService.setNewPassword(otp, password);
	}
	
	

}
