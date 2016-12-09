package com.computing.cloud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.exception.AuthenticationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstanceServiceTest {
	
	@Autowired
	private InstanceService service;
	
	@Test
	public void shouldGetInstance() throws AuthenticationException {
		Instance instance = service.findById(1L);
		assertNotNull(instance.getId());
		assertTrue(instance.getId() > 0);
	}
	
}