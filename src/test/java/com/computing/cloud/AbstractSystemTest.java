package com.computing.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.jayway.restassured.response.Header;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractSystemTest extends AbstractTest {

	@Value("${local.server.port}")
	private int port;

	@Value("${server.context-path}")
	private String context;
	
	protected Header token;
	protected Header usercode;

	protected String getUrl() {
		return "http://localhost:" + port + context;
	}

}
