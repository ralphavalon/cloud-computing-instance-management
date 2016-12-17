package com.computing.cloud.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.exception.AuthenticationException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InstanceServiceTest {
	
	@Autowired
	private InstanceService service;
	
	@Test
	public void shouldGetInstance() throws AuthenticationException {
		Instance instance = service.findById(1L);
		assertNotNull(instance);
		assertNotNull(instance.getPlan());
		assertNotNull(instance.getStorageType());
	}
	
}