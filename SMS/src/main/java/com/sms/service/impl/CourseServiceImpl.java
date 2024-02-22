package com.sms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sms.dto.CourseCreateDTO;
import com.sms.dto.CourseDTO;
import com.sms.dto.CourseInfoDTO;
import com.sms.dto.CourseModifyDTO;
import com.sms.dto.CourseStatusDTO;
import com.sms.dto.PagerDTO;
import com.sms.model.entity.Course;
import com.sms.model.mapper.CourseMapper;
import com.sms.repository.CourseRepository;
import com.sms.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	private final CourseMapper courseMapper;
	private final CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository) {
		this.courseMapper = courseMapper;
		this.courseRepository = courseRepository;
	}
	
	
	@Override
	public Long createCourse(CourseCreateDTO course) {
		Course c = courseMapper.toCourse(course);
		try {
			c = courseRepository.save(c);
			return c.getId() ;
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Long modifyCourse(CourseModifyDTO course) {
		Course c = courseMapper.toCourse(course);
		try {
			c = courseRepository.save(c);
			return c.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Long terminateCourse(Long courseId) {
		try {
			courseRepository.terminateCourse(courseId);
			return courseId;
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public PagerDTO<CourseDTO> getAllActiveCourses(int page, int size) {
		Pageable pager = PageRequest.of(page, size);
		Page<CourseDTO> paged = courseRepository.findCoursesByStatus(1, pager);
		return new PagerDTO<CourseDTO>(paged.getContent(), paged.getTotalPages(), paged.getNumber(), paged.getTotalElements());
	}

	@Override
	public CourseDTO getCourse(Long courseId) {
		Optional<Course> result = courseRepository.findById(courseId);
		if(result.isPresent()) {
			return courseMapper.toCourseDTO(result.get());
		}
		return null;
	}
	

	@Override
	public CourseInfoDTO getCourseInfo(Long courseId) {
		return courseRepository.findCourseInfo(courseId);
	}


	@Override
	public List<CourseDTO> searchCourses(String keyword) {
		List<Course> result = courseRepository.findByNameContainingIgnoreCase(keyword);
		return result.stream().map(courseMapper::toCourseDTO).toList();
	}


	@Override
	public CourseStatusDTO getCourseStatus(Long courseId) {
		Optional<Course> result = courseRepository.findById(courseId);
		if(result.isPresent()) {
			return courseMapper.toCourseStatusDTO(result.get());
		}
		return null;
	}


}
