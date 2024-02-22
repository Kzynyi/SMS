package com.sms.dto;

import java.time.LocalDate;

public record StudentDTO(
		Long id,
		LocalDate enrolledDate,
		int status,
		AccountInfoDTO account
		) {

}
