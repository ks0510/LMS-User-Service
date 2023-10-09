package com.example.demo.model;






import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class UserCandidate {
	

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String fullName;
	private String email;
	private String password;
	private String phoneNumber;
	private boolean active;
	private String otp;
	private LocalTime otpTime;
	private String role;


}
