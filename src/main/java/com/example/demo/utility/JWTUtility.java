package com.example.demo.utility;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.LoginDto;
import com.example.demo.exception.UserCandidateException;
import com.example.demo.model.UserCandidate;
import com.example.demo.repository.IUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility {
	
	@Autowired
	IUserRepo repo;
	
	String secretkey = "kaif@";

	public String generateToken(String email, String Password) {
		
		UserCandidate user = repo.findByEmailAndPassword(email, Password);
		if (user.isActive()==true) {

			HashMap<String, Object> map = new HashMap<>();
			map.put("email", email);
			map.put("password", Password);
			String key = Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.HS256,secretkey).compact();
			return key;
		}
		
		else {
			throw new UserCandidateException("Candidate not verified verify your account first",404);
		}
	}

	public LoginDto decodeToken(String token) {

		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
		} catch (Exception e) {
		throw new UserCandidateException("Invalid token",401);
		}
		Claims decodedToken = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
		LoginDto credentials = new LoginDto();

		credentials.setEmail((String) (decodedToken.get("email")));
		credentials.setPassword((String) (decodedToken.get("password")));

		return credentials;

	}
}





