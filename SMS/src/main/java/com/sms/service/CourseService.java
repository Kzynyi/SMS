package com.sms.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dto.CourseCreateDTO;
import com.sms.dto.CourseDTO;
import com.sms.dto.CourseInfoDTO;
import com.sms.dto.CourseModifyDTO;
import com.sms.dto.CourseStatusDTO;
import com.sms.dto.PagerDTO;

@Service
@Transactional(readOnly = true)
public interface CourseService {

	Long createCourse(CourseCreateDTO course);
	
	Long modifyCourse(CourseModifyDTO course);
	
	Long terminateCourse(Long courseId);
	
	PagerDTO<CourseDTO> getAllActiveCourses(int page, int size);
	
	CourseDTO getCourse(Long courseId);
	
	CourseInfoDTO getCourseInfo(Long courseId);
	
	List<CourseDTO> searchCourses(String keyword);
	
	CourseStatusDTO getCourseStatus(Long courseId);
	
	
}
