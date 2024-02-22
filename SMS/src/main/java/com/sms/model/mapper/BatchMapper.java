package com.sms.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sms.dto.BatchCreateDTO;
import com.sms.dto.BatchDTO;
import com.sms.dto.BatchStatusDTO;
import com.sms.model.entity.Batch;

@Mapper(componentModel = "spring")
public interface BatchMapper {

	BatchStatusDTO toBatchStatusDTO(Batch batch);
	
	BatchDTO toBatchDTO(Batch batch);
	
	@Mapping(target = "students", ignore = true)
	@Mapping(target = "id", ignore = true)
	Batch toBatch(BatchCreateDTO dto);
}
