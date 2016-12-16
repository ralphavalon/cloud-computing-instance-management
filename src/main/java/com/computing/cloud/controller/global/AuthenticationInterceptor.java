package com.computing.cloud.controller.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.computing.cloud.authentication.Authenticate;
import com.computing.cloud.authentication.Encrypter;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.ForbiddenException;
import com.computing.cloud.service.UserService;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod) {
			
			HandlerMethod methodHandler = (HandlerMethod) handler;
			Authenticate authAnnotation = methodHandler.getMethodAnnotation(Authenticate.class);
			
			if( authAnnotation == null) {
				return true;
			}
			
			String token = request.getHeader("token");
			
			if( token == null ) {
				throw new ForbiddenException();
			}
			
			String userExternalId = request.getHeader("usercode");
			System.out.println(userExternalId);
			System.out.println("Token:" );
			System.out.println(token);
			
			String tokenExternalId = Encrypter.decryptToken(token);
			
			User user = userService.findByExternalId(userExternalId);
			
			if( ! user.getExternalId().equals(tokenExternalId) ) {
				throw new ForbiddenException();
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
