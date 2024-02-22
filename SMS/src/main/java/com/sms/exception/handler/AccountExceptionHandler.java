package com.sms.exception.handler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sms.exception.AccountExistsException;

@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleAccountExists(AccountExistsException exception) {
		return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
