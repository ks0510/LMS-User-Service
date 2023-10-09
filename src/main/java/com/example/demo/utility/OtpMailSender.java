package com.example.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class OtpMailSender {
	
	@Autowired
	JavaMailSender javamailSender;
	
	public void sendOtp(String email,String otp) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Account Verfication");
		simpleMailMessage.setText("Hello, Your OTP is "+otp);
		
		javamailSender.send(simpleMailMessage);
		
		
	}

}
