
package com.example.demo.service;

import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.exception.UserCandidateException;
import com.example.demo.model.Response;
import com.example.demo.model.UserCandidate;
import com.example.demo.repository.IUserRepo;
import com.example.demo.utility.JWTUtility;
import com.example.demo.utility.OtpMailSender;
import com.example.demo.utility.OtpUtil;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepo repo;

	@Autowired
	Response response;

	@Autowired
	JWTUtility jwt;

	@Autowired
	OtpUtil otpUtil;

	@Autowired
	OtpMailSender mailSender;

	@Override
	public ResponseEntity<Response> addUser(UserCandidate user) {

		UserCandidate user1 = repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (user1 == null) {

			String otp = otpUtil.generateOtp();

			user.setOtp(otp);
			user.setOtpTime(LocalTime.now());
//			mailSender.sendOtp(user.getEmail(), otp);
			repo.save(user);
			response.setObject(user);
			response.setMessage("User has registred successfully");

			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		}

		else {
			throw new UserCandidateException("Candidate already registered", 404);
		}
	}

	@Override
	public String userLogin(String email, String password) {

		return "You have logged in successfully  " + jwt.generateToken(email, password);
	}

	@Override
	public ResponseEntity<Response> findByEmail(String email, String token) {

		LoginDto login = jwt.decodeToken(token);

		UserCandidate user = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (user == null) {
			throw new UserCandidateException("Invalid Token", 404);
		}

		if (user.getRole().equals("Admin")) {
			response.setObject(repo.findByEmail(email));
			response.setMessage("Candidate found with given email");

			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);

		} else {
			throw new UserCandidateException("Access denied you are not admin", 404);
		}

	}

	@Override
	public ResponseEntity<Response> findByPhoneNumber(String phoneNumber, String token) {

		LoginDto login = jwt.decodeToken(token);

		UserCandidate user = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (user == null) {
			throw new UserCandidateException("Invalid Token", 404);
		}

		if (user.getRole().equals("Admin")) {

			response.setObject(repo.findByPhoneNumber(phoneNumber));
			response.setMessage("Candidate found with given phonenumber");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {
			throw new UserCandidateException("Access denied you are not admin", 404);
		}
	}

	@Override
	public ResponseEntity<Response> findById(int id, String token) {

		LoginDto login = jwt.decodeToken(token);

		UserCandidate user = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (user == null) {
			throw new UserCandidateException("Invalid Token", 404);
		}

		if (user.getRole().equals("Admin")) {

			response.setObject(repo.findById(id));
			response.setMessage("Candidate found with given Id");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new UserCandidateException("Access denied you are not admin", 404);
		}
	}

	@Override
	public String accountVerification(String email, String otp) {
		UserCandidate user = repo.findByEmail(email);
		if (user != null) {
			if (user.isActive()) {
				throw new UserCandidateException("Candidate Already Verified", 404);
			} else {
				java.time.Duration duration = java.time.Duration.between(user.getOtpTime(), LocalTime.now());

				if (user.getOtp().equals(otp) && (duration.toSeconds() < (60 * 5))) {

					user.setActive(true);
					repo.save(user);

					return "Your account verfied succesfully";
				} else {
					throw new UserCandidateException("Invalid otp please regenrate otp", 404);
				}
			}

		} else {
			throw new UserCandidateException("Invalid Email", 404);
		}

	}

	@Override
	public String regenrateOtp(String email, String password) {
		UserCandidate user = repo.findByEmailAndPassword(email, password);
		if (user != null) {
			String otp = otpUtil.generateOtp();
			user.setOtp(otp);
			user.setOtpTime(LocalTime.now());
			mailSender.sendOtp(user.getEmail(), otp);
			repo.save(user);

			return "Regenerated OTP sent to your mail";
		} else {
			throw new UserCandidateException("Invalid email or password", 404);
		}

	}

	@Override
	public String forgotPassword(String email) {
		UserCandidate user = repo.findByEmail(email);
		if (user != null) {
			String otp = otpUtil.generateOtp();
			user.setOtp(otp);
			mailSender.sendOtp(email, otp);
			repo.save(user);

			return "Otp sent to set new  password";
		} else {
			throw new UserCandidateException("Invalid email", 404);
		}
	}

	@Override
	public String setNewPassword(String otp, String password) {
		UserCandidate user = repo.findByOtp(otp);
		if(user!=null) {
			user.setPassword(password);
			repo.save(user);
			return "Password set sucessfully";
		}
		else {
			throw new UserCandidateException("Invalid otp  please enter correct otp",404);
		}
	}

}
