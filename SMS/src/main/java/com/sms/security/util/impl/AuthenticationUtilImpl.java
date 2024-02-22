package com.sms.security.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sms.dto.AccountSignInDTO;
import com.sms.security.util.AuthenticationUtil;
import com.sms.security.util.JwtUtil;

@Component
public class AuthenticationUtilImpl implements AuthenticationUtil {

	
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public AuthenticationUtilImpl(AuthenticationManager authenticationManager,
								  JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public String signIn(AccountSignInDTO dto) {
		Authentication authentication = authenticationManager
										.authenticate(
										new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String token = jwtUtil.generateToken(user);						
		return token;
	}

}
