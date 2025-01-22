package com.cg.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
 
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    String path = request.getRequestURI();
 
	    if (path.startsWith("/login") || path.startsWith("/signup")) {
	        return true;
	    }
 
	    if (request.getSession().getAttribute("user") == null) {
	        response.sendRedirect("/login");
	        return false;
	    }

	    return true;
	}
 
 
}