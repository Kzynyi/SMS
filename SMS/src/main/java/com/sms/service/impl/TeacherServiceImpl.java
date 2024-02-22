package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sms.dto.TeacherCreateDTO;
import com.sms.dto.TeacherDTO;
import com.sms.dto.TeacherStatusDTO;
import com.sms.model.entity.Account;
import com.sms.model.entity.Teacher;
import com.sms.model.mapper.TeacherMapper;
import com.sms.repository.TeacherRepository;
import com.sms.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final TeacherMapper teacherMapper;
	private final TeacherRepository teacherRepository;
	
	public TeacherServiceImpl(TeacherMapper teacherMapper, TeacherRepository teacherRepository) {
		this.teacherMapper = teacherMapper;
		this.teacherRepository = teacherRepository;
	}

	@Override
	public Long createTeacherAccount(TeacherCreateDTO dto) {
		Teacher teacher = teacherMapper.toTeacher(dto);
		try {
			teacher = teacherRepository.save(teacher);
			return teacher.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public Optional<Teacher> getTeacherByEmail(String email) {
		return teacherRepository.findByEmail(email);
	}

	@Override
	public Long retireTeacher(Long teacherId) {
		try {
			teacherRepository.retireTeacher(teacherId);
			return teacherId;
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public List<TeacherDTO> getAllActiveTeacher() {
		return teacherRepository.findAllActiveTeacher();
	}

	@Override
	public TeacherStatusDTO getTeacherStatus(Long teacherId) {
		Optional<Teacher> result = teacherRepository.findById(teacherId);
		if(result.isPresent()) {
			TeacherStatusDTO teacher = teacherMapper.toTeacherStatusDTO(result.get());
			return teacher;
		}
		return null;
	}

}
