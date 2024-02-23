package com.sms.dto;

public record ModuleCreateDTO(
		String name,
		String description,
		Long courseId,
		int status
		) {

}
