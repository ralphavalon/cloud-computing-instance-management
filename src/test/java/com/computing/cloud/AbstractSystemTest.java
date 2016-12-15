package com.computing.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractSystemTest {

	@Value("${local.server.port}")
	private int port;

	@Value("${server.context-path}")
	private String context;

	@Autowired
	private TestRestTemplate restTemplate;

	protected String getUrl() {
		return "http://localhost:" + port + context;
	}

}
