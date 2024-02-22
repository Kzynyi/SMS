package com.sms.util.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sms.model.entity.Account;
import com.sms.repository.AccountRepository;
import com.sms.util.AccountUtil;

@Component
public class AccountUtilImpl implements AccountUtil{

	private final AccountRepository accountRepository;
	
	public AccountUtilImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public boolean checkAccountExists(String email, String phone) {
		Optional<Account> result = accountRepository.findByEmailOrPhone(email, phone);
		if(result.isEmpty()) {
			return false;
		}
		return true;
	}

	
}
