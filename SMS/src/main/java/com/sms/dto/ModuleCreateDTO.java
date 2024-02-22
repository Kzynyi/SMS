package com.sms.dto;

public record ModuleCreateDTO(
		String name,
		String description,
		CourseDTO course,
		int status
		) {

}
