package com.sms.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.AccountSignInDTO;
import com.sms.dto.AccountSignUpDTO;
import com.sms.exception.AccountExistsException;
import com.sms.security.util.AuthenticationUtil;
import com.sms.service.AccountService;

@RestController
@RequestMapping("/api/auth")
public class ClientAuthController {

	private final AccountService accountService;
	private final AuthenticationUtil authenticationUtil;
	
	public ClientAuthController(AccountService accountService,
								AuthenticationUtil authenticationUtil) {
		this.accountService = accountService;
		this.authenticationUtil = authenticationUtil;
	}

	@PostMapping("/accounts")
	public ResponseEntity<Map<String, String>> accountSignUp(@RequestBody AccountSignUpDTO dto) throws AccountExistsException {
		Long result = accountService.accountSignUp(dto);
		if(result == 0L) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Account sign up failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return ResponseEntity.ok(Collections.singletonMap("message", "Account created successfully"));
		}
	}
	
	@PostMapping("/sessions")
	public ResponseEntity<Map<String, String>> accountSignIn(@RequestBody AccountSignInDTO dto) {
		String token = authenticationUtil.signIn(dto);
		return ResponseEntity.ok(Collections.singletonMap("token", token));
	}
	
}
