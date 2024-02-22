package com.sms.model.mapper;

import org.mapstruct.Mapper;

import com.sms.dto.StudentDTO;
import com.sms.model.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	StudentDTO toStudentDTO(Student student);
}
