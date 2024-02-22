package com.sms.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dto.CourseCreateDTO;
import com.sms.dto.CourseDTO;
import com.sms.dto.CourseModifyDTO;
import com.sms.dto.CourseStatusDTO;
import com.sms.dto.PagerDTO;
import com.sms.service.CourseService;

@RestController
@RequestMapping("/api/admin")
public class AdminCourseController {

	
	private final CourseService courseService;

	public AdminCourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	
	@GetMapping("/courses")
	public ResponseEntity<PagerDTO<CourseDTO>> allCourses(@RequestParam(defaultValue = "0") int page,
			  @RequestParam(defaultValue = "10") int size) {
		PagerDTO<CourseDTO> courses = courseService.getAllActiveCourses(page, size);
		return ResponseEntity.ok(courses);
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseStatusDTO> oneCourse(@PathVariable("id") String id) {
		Long courseId = Long.valueOf(id);
		CourseStatusDTO course = courseService.getCourseStatus(courseId);
		return ResponseEntity.ok(course);
	}
	
	@PostMapping("/courses")
	public ResponseEntity<Map<String, String>> createCourse(@RequestBody CourseCreateDTO dto) {
		Long cid = courseService.createCourse(dto);
		if(cid == 0L) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Course creation failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(Collections.singletonMap("message", "Course successfully created"));
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<Map<String, String>> updateCourse(CourseModifyDTO dto) {
		Long cid = courseService.modifyCourse(dto);
		if(cid == 0L) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Course update failed") , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(Collections.singletonMap("message", "Course successfully updated"));
	}
	
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Map<String, String>> terminateCourse(@PathVariable("id") String id) {
		Long courseId = Long.valueOf(id);
		Long result = courseService.terminateCourse(courseId);
		if(result == 0L) {
			return new ResponseEntity<>(Collections.singletonMap("message",  "Course terminate failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(Collections.singletonMap("message", "Course successfully terminate"));
	}
	
}
