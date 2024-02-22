package com.sms.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.CourseDTO;
import com.sms.dto.CourseInfoDTO;
import com.sms.dto.PagerDTO;
import com.sms.service.CourseService;

@RestController
@RequestMapping("/api")
public class ClientCourseController {

	private final CourseService courseService;
	
	public ClientCourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/courses")
	public ResponseEntity<PagerDTO<CourseDTO>> allCourses(@RequestParam(defaultValue = "0") int page,
													  @RequestParam(defaultValue = "10") int size) {
		PagerDTO<CourseDTO> courses = courseService.getAllActiveCourses(page, size);
		return ResponseEntity.ok(courses);
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseInfoDTO> oneCourse(@PathVariable("id") String id) {
		Long courseId = Long.valueOf(id);
		CourseInfoDTO course = this.courseService.getCourseInfo(courseId);
		return ResponseEntity.ok(course);
	}
}
