package com.portfolio.main.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/*
 * 토큰의 권한이 낮을경우 리다이렉트
 * 
 */

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException,
	ServletException {
		response.sendRedirect("/exception/accessdenied");
	}
}

