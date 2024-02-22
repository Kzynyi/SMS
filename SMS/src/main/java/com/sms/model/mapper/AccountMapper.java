package com.sms.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sms.dto.AccountInfoDTO;
import com.sms.dto.AccountModifyDTO;
import com.sms.dto.AccountSignUpDTO;
import com.sms.model.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	AccountInfoDTO toAccountInfoDTO(Account account);
	
	@Mapping(target = "id", ignore = true)
	Account toAccount(AccountSignUpDTO dto);
	
	@Mapping(target = "dob", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "password", ignore = true)
	Account toAccount(AccountModifyDTO dto);
}
