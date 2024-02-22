package com.sms.dto;

import java.time.LocalDate;

public record BatchDTO(
		Long id,
		String name,
		LocalDate startDate,
		int studentLimit,
		int status
		) {

}
