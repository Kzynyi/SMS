package com.sms.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.BatchCreateDTO;
import com.sms.service.BatchService;

@RestController
@RequestMapping("/api/admin")
public class AdminBatchController {

	private final BatchService batchService;
	
	public AdminBatchController(BatchService batchService) {
		this.batchService = batchService;
	}



	@PostMapping("/batches")
	public ResponseEntity<Map<String, String>> createBatch(@RequestBody BatchCreateDTO dto) {
		Long bid = batchService.createBatch(dto);
		if(bid == 0L) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(Collections.singletonMap("message", "Batch creation failed"));
		}
		return ResponseEntity.ok(Collections.singletonMap("message", "Batch created successfully"));
	}

}
