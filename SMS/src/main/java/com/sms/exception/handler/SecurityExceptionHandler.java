package com.sms.exception.handler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException exception) {
		return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException exception) {
		return new ResponseEntity<>(Collections.singletonMap("message", "Email or password is incorrect"), HttpStatus.UNAUTHORIZED);
	}
	
	public ResponseEntity<Map<String, String>> handleJwtExpired(ExpiredJwtException exception) {
		return new ResponseEntity<>(Collections.singletonMap("message", "Token expired"), HttpStatus.FORBIDDEN);
	}
	
}
