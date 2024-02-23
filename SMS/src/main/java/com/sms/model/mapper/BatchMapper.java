package com.sms.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sms.dto.BatchCreateDTO;
import com.sms.dto.BatchDTO;
import com.sms.dto.BatchStatusDTO;
import com.sms.model.entity.Batch;
import com.sms.model.mapper.util.BatchMapperUtil;
import com.sms.repository.CourseRepository;

@Mapper(componentModel = "spring", uses = {BatchMapperUtil.class})
public interface BatchMapper {

	BatchStatusDTO toBatchStatusDTO(Batch batch);
	
	BatchDTO toBatchDTO(Batch batch);
	
	@Mappings({
		@Mapping(target = "students", ignore = true),
		@Mapping(target = "id", ignore = true),
		@Mapping(source = "courseId", target = "course"),
		@Mapping(source = "teacherId", target = "teacher")
	})
	Batch toBatch(BatchCreateDTO dto);
}
