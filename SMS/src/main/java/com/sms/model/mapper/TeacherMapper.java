package com.sms.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sms.dto.TeacherCreateDTO;
import com.sms.dto.TeacherStatusDTO;
import com.sms.model.entity.Teacher;

@Mapper
public interface TeacherMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "batches", ignore = true)
	Teacher toTeacher(TeacherCreateDTO dto);
	
	TeacherStatusDTO toTeacherStatusDTO(Teacher teacher);
	
}
