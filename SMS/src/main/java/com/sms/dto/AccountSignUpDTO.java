package com.sms.dto;

public record AccountSignUpDTO(
		String name,
		String dob,
		String address,
		String phone,
		String email,
		String password,
		int status
		) {

}
