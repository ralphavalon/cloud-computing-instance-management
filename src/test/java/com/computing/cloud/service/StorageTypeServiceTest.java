package com.computing.cloud.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.domain.StorageType;
import com.computing.cloud.exception.AuthenticationException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StorageTypeServiceTest {
	
	@Autowired
	private StorageTypeService service;
	
	@Test
	public void shouldGetStorageType() throws AuthenticationException {
		StorageType storageType = service.findById(1L);
		assertNotNull(storageType);
	}
	
}