package com.sms.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminModuleController {

	public ResponseEntity<Collection<Map<String, String>>> createModule(){
		
	}
	
}
