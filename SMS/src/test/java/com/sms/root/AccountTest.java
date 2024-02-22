package com.sms.root;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.sms.dto.AccountInfoDTO;
import com.sms.dto.AccountModifyDTO;
import com.sms.dto.AccountSignUpDTO;
import com.sms.exception.AccountExistsException;
import com.sms.service.AccountService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTest {

	static PostgreSQLContainer<?> pgs = new PostgreSQLContainer<>("postgres:latest");
	
	@Autowired
	private AccountService accountService;
	
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
	
	@Test
	@Order(1)
	void createAccountTest() throws AccountExistsException {
		AccountSignUpDTO dto = new AccountSignUpDTO("John Doe", "12/12/2000", "Home", "09794437728", 
								"johndoe@mail.com", "johndoe@123", 1);
		Long id = accountService.accountSignUp(dto);
		assertEquals(1, id);
	}
	
	@Test
	@Order(2)
	void modifyAccountTest() {
		AccountModifyDTO dto = new AccountModifyDTO(1L, "johndoe@mail.com", "South Korea", "09794427728");
		Long res = accountService.editAccountInfo(dto);
		assertEquals(1, res);
	}
	
	@Test
	@Order(3)
	void findAccountTest() {
		AccountInfoDTO dto = accountService.getAccountById(1L);
		assertEquals("John Doe", dto.name());
		assertEquals("South Korea", dto.address());
	}
}
