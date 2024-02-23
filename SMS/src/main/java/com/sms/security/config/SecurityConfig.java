package com.sms.security.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sms.model.entity.Account;
import com.sms.security.filter.JwtSecurityFilter;
import com.sms.security.util.JwtUtil;
import com.sms.service.AccountService;
import com.sms.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@Slf4j
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, 
						AuthenticationManager authenticationManager,
						JwtSecurityFilter jwtSecurityFilter,
						AuthenticationEntryPoint authenticationEntryPoint) throws Exception {
		
		http.authorizeHttpRequests(				
		authorize -> authorize.requestMatchers("/api/auth/**")
		.permitAll()
		.requestMatchers("/api/admin/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		);
		http.csrf(csrf -> csrf.disable());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		http.authenticationManager(authenticationManager);
		http.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
		return http.build();
	}
	
	@Bean(name = "uService")
	public UserDetailsService userDetailsService(AccountService accountService) {
		UserDetailsService uds = (String email) -> {
			Optional<Account> account = accountService.getAccountByEmail(email);
			if(account.isEmpty()) {
				String error = String.format("Email %s not found", email);
				throw new UsernameNotFoundException(error);
			}
			return account.get();
							
		};
		return uds;
	}
	
	@Bean(name = "tService")
	public UserDetailsService teacherUserDetailsService(TeacherService teacherService) {
		UserDetailsService uds = (String email) -> {
			return teacherService.getTeacherByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found"));
		};
		return uds;
	}
	
	@Bean(name = "userAuthProvider")
	public AuthenticationProvider authenticationProvider(@Qualifier("uService") UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean(name = "teacherAuthProvider")
	public AuthenticationProvider teacherAuthProvider(@Qualifier("tService") UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, 
									AuthenticationProvider userAuthProvider, 
									AuthenticationProvider teacherAuthProvider) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.authenticationProvider(userAuthProvider)
				.authenticationProvider(teacherAuthProvider)
				.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedOrigins(Arrays.asList("*"));
		
		cors.setAllowedMethods(Arrays.asList("*"));
		cors.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cors);
		return source;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtSecurityFilter jwtSecurityFilter(JwtUtil jwtUtil,@Qualifier("uService") UserDetailsService userDetailsService) {
		return new JwtSecurityFilter(jwtUtil, userDetailsService);
	}
}
