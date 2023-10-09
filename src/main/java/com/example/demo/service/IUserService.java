package com.example.demo.service;



import org.springframework.http.ResponseEntity;
import com.example.demo.model.Response;
import com.example.demo.model.UserCandidate;

public interface IUserService {
	
	public ResponseEntity<Response> addUser(UserCandidate user);
	
	public String userLogin(String email,String password);
	
	public ResponseEntity<Response> findByEmail(String email,String token);
	
	public ResponseEntity<Response> findByPhoneNumber(String phoneNumber,String token);
	
	public ResponseEntity<Response> findById(int id,String token);
	
	public String accountVerification(String email,String otp);
	
	public String regenrateOtp(String email,String password);
	
	public String forgotPassword(String email);
	
	public String setNewPassword(String otp,String password);

}
