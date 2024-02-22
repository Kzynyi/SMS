package com.sms.security.util.impl;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sms.security.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilImpl implements JwtUtil {

	private Long expiration = 3600000L;
	private SecretKey secretKey;
	
	public JwtUtilImpl() {
		this.secretKey = getSigningKey();
	}
	
	@Override
	public String generateToken(UserDetails userDetails) {
							
		return Jwts
		.builder()
		.subject(userDetails.getUsername())
		.issuedAt(new Date())
		.issuer("SMS")
		.expiration(new Date(System.currentTimeMillis() + expiration))
		.signWith(secretKey)
		.compact();
	}

	@Override
	public boolean validateToken(String token, UserDetails userDetails) {
		boolean valid = userDetails.getUsername().equals(getSubject(token))
						&& !isExpired(token);
		return valid;
	}

	@Override
	public Claims parseClaims(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	}
	

	@Override
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	@Override
	public boolean isExpired(String token) {
		boolean expired =  parseClaims(token).getExpiration().before(new Date());
		return expired;
	}

	@Override
	public SecretKey getSigningKey() {
		return Jwts.SIG.HS256.key().build();
	}


}
