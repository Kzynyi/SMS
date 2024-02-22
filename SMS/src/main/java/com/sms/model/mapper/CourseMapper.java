package com.sms.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sms.dto.BatchStatusDTO;
import com.sms.dto.CourseCreateDTO;
import com.sms.dto.CourseDTO;
import com.sms.dto.CourseModifyDTO;
import com.sms.dto.CourseStatusDTO;
import com.sms.model.entity.Batch;
import com.sms.model.entity.Course;
import com.sms.repository.CourseRepository;

@Mapper(componentModel = "spring", uses = {CourseRepository.class})
public interface CourseMapper {

	CourseDTO toCourseDTO(Course course);
	
	CourseStatusDTO toCourseStatusDTO(Course course);
	
	@Mapping(target = "id", ignore = true)
	Course toCourse(CourseCreateDTO dto);

	Course toCourse(CourseModifyDTO dto);
	
	
}
