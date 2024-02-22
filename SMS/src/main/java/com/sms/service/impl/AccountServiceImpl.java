package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dto.AccountInfoDTO;
import com.sms.dto.AccountListItemDTO;
import com.sms.dto.AccountModifyDTO;
import com.sms.dto.AccountSignUpDTO;
import com.sms.exception.AccountExistsException;
import com.sms.model.entity.Account;
import com.sms.model.mapper.AccountMapper;
import com.sms.repository.AccountRepository;
import com.sms.service.AccountService;
import com.sms.util.AccountUtil;

@Service
public class AccountServiceImpl implements AccountService {

	
	private final AccountRepository accountRepository;
	private final AccountMapper accountMapper;
	private final AccountUtil accountUtil;
	private final PasswordEncoder passwordEncoder;
	
	public AccountServiceImpl(AccountRepository accountRepository, 
							AccountMapper accountMapper,
							AccountUtil accountUtil,
							PasswordEncoder passwordEncoder) {
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
		this.accountUtil = accountUtil;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Long accountSignUp(AccountSignUpDTO dto) throws AccountExistsException {
		if(accountUtil.checkAccountExists(dto.email(), dto.phone())) {
			String message = "An account with email "+dto.email()+
							" or phone "+dto.phone()+" already exists";
			throw new AccountExistsException(message);
		}
		Account account = accountMapper.toAccount(dto);
		account.setPassword(passwordEncoder.encode(dto.password()));
		try {
			account = this.accountRepository.save(account);
			return account.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Long editAccountInfo(AccountModifyDTO dto) {

		try {
			this.accountRepository.modifyAccount(dto.id(), 
												dto.email(), dto.phone(), dto.address());
			return dto.id();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public Long changePassword(Long accountId, String oldPassword, String newPassword) {
		Optional<Account> result = accountRepository.findById(accountId);
		if(result.isPresent()) {
			Account account = result.get();
			if(account.getPassword().equals(passwordEncoder.encode(oldPassword))) {
				accountRepository.changePassword(accountId, passwordEncoder.encode(newPassword));
				return account.getId();
			}
		}
		return 0L;
	}

	@Override
	public List<AccountListItemDTO> getAllAccounts() {
		return accountRepository.findAllAccounts();
	}

	@Override
	public AccountInfoDTO getAccountById(Long accountId) {
		Optional<Account> result = accountRepository.findById(accountId);
		if(result.isPresent()) {
			return accountMapper.toAccountInfoDTO(result.get());
		}
		return null;
	}

	@Override
	public Optional<Account> getAccountByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
	@Override
	public Long banAccount(Long accountId) {
		try {
			accountRepository.banAccount(accountId);
			return 1L;
		}catch(Exception e) {
			return 0L;
		}
	}



	
	
}
