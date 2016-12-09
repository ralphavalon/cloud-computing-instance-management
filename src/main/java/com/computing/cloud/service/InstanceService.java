package com.computing.cloud.service;

import com.computing.cloud.domain.Instance;

public interface InstanceService {
	
	Instance findById(Long id);

}