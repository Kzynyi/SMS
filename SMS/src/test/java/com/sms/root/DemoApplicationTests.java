package com.sms.root;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import com.sms.dto.BatchCreateDTO;
import com.sms.dto.CourseCreateDTO;
import com.sms.dto.CourseDTO;
import com.sms.dto.CourseStatusDTO;
import com.sms.dto.ModuleCreateDTO;
import com.sms.service.BatchService;
import com.sms.service.CourseService;
import com.sms.service.ModuleService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {

	static PostgreSQLContainer<?> pgs = new PostgreSQLContainer<>("postgres:latest");
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private BatchService batchService;
	
	@Autowired
	private ModuleService moduleService;
	
	@BeforeAll
	static void before() {
		pgs.start();
	}
	
	@AfterAll
	static void after() {
		pgs.stop();
	}
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", pgs::getJdbcUrl);
	    registry.add("spring.datasource.username", pgs::getUsername);
	    registry.add("spring.datasource.password", pgs::getPassword);
	}
	
	/*
	@Test
	@Order(1)
	void createCourseTest() {
		CourseCreateDTO dto = new CourseCreateDTO("Python basics", "Learning basics of python", 1);
		Long res = courseService.createCourse(dto);
		CourseDTO course = new CourseDTO(res, dto.name(), dto.description(), dto.status());
		assertEquals(1, res);
		BatchCreateDTO bdto = new BatchCreateDTO(
				"Python basics Batch 1",
				LocalDate.now(),
				50,
				1,
				course
				);
		Long bres = batchService.createBatch(bdto);
		assertEquals(1, bres);
		ModuleCreateDTO setup = new ModuleCreateDTO("Python setup", "Installing python in your local device", course, 1);
		ModuleCreateDTO loop = new ModuleCreateDTO("Python loops", "Learning about iteration", course, 1);
		Long m1 = moduleService.createModule(setup);
		Long m2 = moduleService.createModule(loop);
		assertEquals(1, m1);
		assertEquals(2, m2);
	}
	*/
	
	/*
	@Test
	@Order(2)
	void selectCourseTest() {
		List<CourseDTO> list = courseService.getAllActiveCourses(1, 10);
		assertEquals(1,list.size());
	}
	*/
	@Test
	@Order(3)
	void courseStatusTest() {
		CourseStatusDTO dto = courseService.getCourseStatus(1L);
		assertNotNull(dto);
		System.out.println(dto.toString());
		assertEquals(1, dto.batches().size());
		assertEquals(2, dto.modules().size());
	}

}
