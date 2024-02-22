package com.sms.dto;

import java.util.List;

public record CourseInfoDTO(
		String name,
		String description,
		List<ModuleDTO> modules,
		List<BatchDTO> batches
		) {

}
