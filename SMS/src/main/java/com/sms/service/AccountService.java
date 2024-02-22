package com.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sms.dto.AccountInfoDTO;
import com.sms.dto.AccountListItemDTO;
import com.sms.dto.AccountModifyDTO;
import com.sms.dto.AccountSignUpDTO;
import com.sms.exception.AccountExistsException;
import com.sms.model.entity.Account;

@Service
public interface AccountService {

	Long accountSignUp(AccountSignUpDTO dto) throws AccountExistsException;
	
	Long editAccountInfo(AccountModifyDTO dto);
	
	Long changePassword(Long accountId, String oldPassword, String newPassword);
	
	List<AccountListItemDTO> getAllAccounts();
	
	AccountInfoDTO getAccountById(Long accountId);
	
	Optional<Account> getAccountByEmail(String email);
	
	Long banAccount(Long accountId);
	
	
	
	
	
	
	
	
	
	
}
