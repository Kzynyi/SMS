package com.sms.root;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import io.restassured.RestAssured;
import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestTest {

	static PostgreSQLContainer<?> pgs = new PostgreSQLContainer<>("postgres:latest");
	
	@LocalServerPort
	private Integer port;
	
	@BeforeAll
	static void before() {
		pgs.start();
	}
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost:"+port;
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
	
	@Test
	@Order(1)
	void signUpTest() {
		Map<String, Object> signUp = new HashMap<>(); 
		signUp.put("name", "John Doe");
		signUp.put("address", "Teacup Street, London, England");
		signUp.put("dob", "07/08/2003");
		signUp.put("phone", "+2533113408023");
		signUp.put("email", "johndoe11@mail.com");
		signUp.put("password", "jhpass@1212!");
		signUp.put("status", 1);
		given()
		.contentType("application/json")
		.body(signUp)
		.when()
		.post("/api/account/signup")
		.then()
		.statusCode(200);
	}
	
	@Test
	@Order(2)
	void getAllAccountTest() {
		given()
		.when()
		.get("/api/admin/account/all")
		.then()
		.statusCode(200);
	}
	
}
