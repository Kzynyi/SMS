package com.sms.dto;

import java.util.List;

public record CourseStatusDTO(
		String name,
		String description,
		int status,
		List<BatchDTO> batches,
		List<ModuleDTO> modules
		) {

}
