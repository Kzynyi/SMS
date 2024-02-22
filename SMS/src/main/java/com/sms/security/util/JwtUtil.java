package com.sms.security.util;

import java.security.Key;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

public interface JwtUtil {

	String generateToken(UserDetails userDetails);
	
	boolean validateToken(String token, UserDetails userDetails);
	
	Claims parseClaims(String token);
	
	String getSubject(String token);
	
	boolean isExpired(String token);
	
	SecretKey getSigningKey();
}
