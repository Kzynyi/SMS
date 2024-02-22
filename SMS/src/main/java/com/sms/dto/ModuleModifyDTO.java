package com.sms.dto;

public record ModuleModifyDTO(
		Long id,
		String name,
		String description,
		CourseDTO course,
		int status
		) {

}
