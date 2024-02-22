package com.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sms.dto.TeacherCreateDTO;
import com.sms.dto.TeacherDTO;
import com.sms.dto.TeacherStatusDTO;
import com.sms.model.entity.Account;
import com.sms.model.entity.Teacher;

@Service
public interface TeacherService {

	Long createTeacherAccount(TeacherCreateDTO dto);
	
	Optional<Teacher> getTeacherByEmail(String email);
	
	Long retireTeacher(Long teacherId);
	
	List<TeacherDTO> getAllActiveTeacher();
	
	TeacherStatusDTO getTeacherStatus(Long teacherId);
	
	
}
