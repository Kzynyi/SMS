package com.sms.model.mapper.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sms.model.entity.Course;
import com.sms.model.entity.Teacher;
import com.sms.repository.CourseRepository;
import com.sms.repository.TeacherRepository;

@Component
public class BatchMapperUtil {

	private final CourseRepository courseRepository;
	private final TeacherRepository teacherRepository;

	public BatchMapperUtil(CourseRepository courseRepository, TeacherRepository teacherRepository) {
		this.courseRepository = courseRepository;
		this.teacherRepository = teacherRepository;
	}
	
	public Course getCourseById(Long courseId) {
		Optional<Course> result = courseRepository.findById(courseId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	public Teacher getTeacherById(Long teacherId) {
		Optional<Teacher> result = teacherRepository.findById(teacherId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	
}
