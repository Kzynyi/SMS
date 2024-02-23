package com.sms.model.mapper.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sms.model.entity.Course;
import com.sms.repository.CourseRepository;

@Component
public class ModuleMapperUtil {

	private final CourseRepository courseRepository;

	public ModuleMapperUtil(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	
	public Course getCourseById(Long courseId) {
		Optional<Course> result = courseRepository.findById(courseId);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	
}
