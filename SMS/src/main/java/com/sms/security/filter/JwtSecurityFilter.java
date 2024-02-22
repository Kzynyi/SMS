package com.sms.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sms.security.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
	@Autowired
	public JwtSecurityFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String email;
		final String token;
		if(!hasBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		token = getToken(request);
		email = jwtUtil.getSubject(token);
		if(!email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = userDetailsService.loadUserByUsername(email);
			if(jwtUtil.validateToken(token, user)) {
			log.warn("valid");
			var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	private boolean hasBearer(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(authHeader == null) {
			return false;
		}else if( authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
			return false;
		}		
		return true;
	}

	private String getToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		String jwt = authHeader.substring(7);
		return jwt;
	}
	
}
