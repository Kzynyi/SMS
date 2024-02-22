package com.sms.dto;

import java.time.LocalDate;

public record BatchStatusDTO(
		String name,
		LocalDate startDate,
		int studentLimit,
		int status
		) {

}
