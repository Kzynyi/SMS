package com.sms.dto;

public record AccountModifyDTO(
		Long id,
		String email,
		String address,
		String phone
		) {

}
