package com.tekmexico.notes.data.services;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class AuthService {
	
	private static Key key = MacProvider.generateKey();
	
	private static String subject = "The Big Red Fox";
	
	public String getJwt(Integer userId) {
		String s = Jwts.builder().setSubject(AuthService.subject).signWith(SignatureAlgorithm.HS512, AuthService.key).claim("userId", userId).compact();
		return s;
	}
	
	public Boolean testJwt(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
			if(claims.getBody().getSubject().equals(AuthService.subject))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println("Bad Token: " + e);
			return false;
		}
	}
	
	public Integer getUserId(String jwt) {
		Integer userId = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody().get("userId", Integer.class);
		System.out.println("User Id: " + userId);
		return userId;
	}
}
