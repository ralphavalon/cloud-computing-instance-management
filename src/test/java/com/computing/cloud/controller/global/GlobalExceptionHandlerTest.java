package com.computing.cloud.controller.global;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

	GlobalExceptionHandler exceptionHandler;
	
	@Before
	public void setUp() {
		exceptionHandler = new GlobalExceptionHandler();
	}
	
	@Test
	public void generic_exception_handler() {
		ResponseEntity<Map<String, String>> response = exceptionHandler.genericError(new Exception("Mensagem de Error"));
		
		assertEquals( HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode() );
		assertEquals( "Mensagem de Error", response.getBody().get("message") );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void bad_request_exception_handler() {
		ResponseEntity<Map<String, Object>> response = exceptionHandler.badRequest(new RuntimeException("Erro de request qualquer"));
		
		assertEquals( HttpStatus.BAD_REQUEST, response.getStatusCode() );
		assertEquals( "Invalid json for this action", response.getBody().get("message") );
		List<String> actual = (List<String>) response.getBody().get("errorsList");
		assertEquals( 1, actual.size() );
	}
	
}
