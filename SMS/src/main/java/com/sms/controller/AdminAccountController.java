package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.AccountInfoDTO;
import com.sms.dto.AccountListItemDTO;
import com.sms.service.AccountService;

@RestController
@RequestMapping("/api/admin")
public class AdminAccountController {

	private final AccountService accountService;

	@Autowired
	public AdminAccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<List<AccountListItemDTO>> getAccountList() {
		List<AccountListItemDTO> list = accountService.getAllAccounts();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/accounts/{id}")
	public ResponseEntity<AccountInfoDTO> getAccount(@PathVariable("id") String id) {
		AccountInfoDTO dto = accountService.getAccountById(Long.valueOf(id)); 
		return ResponseEntity.ok(dto);
	}
	
}
