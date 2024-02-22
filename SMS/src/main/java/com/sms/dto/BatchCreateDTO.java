package com.sms.dto;

import java.time.LocalDate;

public record BatchCreateDTO(
		String name,
		LocalDate startDate,
		int studentLimit,
		int status,
		int courseId
		) {

}
