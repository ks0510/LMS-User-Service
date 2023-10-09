package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.UserCandidate;

public interface IUserRepo extends JpaRepository<UserCandidate,Integer> {
	
	public UserCandidate findByEmail(String email);
	
	public UserCandidate findByPhoneNumber(String email);
	
	public UserCandidate findById(int id);
	
	public UserCandidate findByEmailAndPassword(String email,String password);
	
	public UserCandidate findByOtp(String otp);

}
