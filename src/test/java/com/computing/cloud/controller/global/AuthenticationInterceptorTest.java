package com.computing.cloud.controller.global;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.authentication.Authenticate;
import com.computing.cloud.authentication.Encrypter;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.ForbiddenException;
import com.computing.cloud.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationInterceptorTest extends AbstractTest {

	@InjectMocks
	private AuthenticationInterceptor interceptor;
	
	@Mock
	private UserService userService;
	private User user;
	
	@Before @After
	public void resetId() {
		user = userThree;
		user.setExternalId(null);
	}
	
	@Test
	public void authenticate() throws Exception {
		final String EXTERNAL_ID = UUID.randomUUID().toString();
		HandlerMethod handler = mock(HandlerMethod.class);
		when( handler.getMethodAnnotation(Authenticate.class) ).thenReturn( getInstanceOfAuthenticateAnnotation() );
		
		user.setExternalId(EXTERNAL_ID);
		when( userService.findByExternalId(EXTERNAL_ID) ).thenReturn( user );
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		MockHttpServletRequest request = mockHttpServletRequest(EXTERNAL_ID, Encrypter.encryptToken(EXTERNAL_ID));
		
		interceptor.preHandle(request , response, handler);
		
		verify( userService, times(1) ).findByExternalId(EXTERNAL_ID);
	}
	
	@Test(expected=ForbiddenException.class)
	public void error_authenticate_with_token_from_another_user() throws Exception {
		final String EXTERNAL_ID = UUID.randomUUID().toString();
		HandlerMethod handler = mock(HandlerMethod.class);
		when( handler.getMethodAnnotation(Authenticate.class) ).thenReturn( getInstanceOfAuthenticateAnnotation() );
		
		user.setExternalId(EXTERNAL_ID);
		when( userService.findByExternalId(EXTERNAL_ID) ).thenReturn( user );
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		MockHttpServletRequest request = mockHttpServletRequest(EXTERNAL_ID, Encrypter.encryptToken("OUTRO_EXTERNAL_ID"));
		
		interceptor.preHandle(request , response, handler);
	}
	
	@Test(expected=ForbiddenException.class)
	public void error_authenticate_without_token() throws Exception {
		final String EXTERNAL_ID = UUID.randomUUID().toString();
		HandlerMethod handler = mock(HandlerMethod.class);
		when( handler.getMethodAnnotation(Authenticate.class) ).thenReturn( getInstanceOfAuthenticateAnnotation() );
		
		user.setExternalId(EXTERNAL_ID);
		when( userService.findByExternalId(EXTERNAL_ID) ).thenReturn( user );
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		MockHttpServletRequest request = mockHttpServletRequest(EXTERNAL_ID, null);
		
		interceptor.preHandle(request , response, handler);
	}
	
	@Test
	public void do_not_authenticate_if_method_does_not_have_annotation() throws Exception {
		final String EXTERNAL_ID = UUID.randomUUID().toString();
		HandlerMethod handler = mock(HandlerMethod.class);
		when( handler.getMethodAnnotation(Authenticate.class) ).thenReturn( null );
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		MockHttpServletRequest request = mockHttpServletRequest(EXTERNAL_ID, Encrypter.encryptToken(EXTERNAL_ID));
		interceptor.preHandle(request , response, handler);
		
		verify( userService, times(0) ).findByExternalId(EXTERNAL_ID);
	}
	
	private MockHttpServletRequest mockHttpServletRequest(String externalId, String token) {
		MockHttpServletRequest request = new MockHttpServletRequest();
		
	    request.addHeader("usercode", externalId);
	    
		if( token != null ) {
			request.addHeader("token", token);
		}
		return request;
	}
	
	private static Authenticate getInstanceOfAuthenticateAnnotation() throws Exception {
	    final class c { @Authenticate public void a() {} }
	    return c.class.getMethod("a").getAnnotation(Authenticate.class);
	}
	
}
