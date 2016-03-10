package com.tekmexico.notes.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tekmexico.notes.data.services.AuthService;

public class NoteApiInterceptor extends HandlerInterceptorAdapter {
	
	private AuthService auth = new AuthService();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Running Interceptor");
		System.out.println("request.Authorization:" + request.getHeader("Authorization"));
		
		try {
			
			request.setAttribute("userId", this.auth.getUserId(request.getHeader("Authorization")));
		} catch(Exception e) {
			response.sendError(401);
		}
		return super.preHandle(request, response, handler);
	}
}
