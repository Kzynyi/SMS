package com.sms.dto;

import java.util.List;

public record TeacherStatusDTO(
		String name,
		String email,
		List<BatchDTO> batches
		) {

}
