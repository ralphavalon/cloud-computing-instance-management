package com.computing.cloud.dao;

import com.computing.cloud.domain.Instance;

public interface InstanceRepository {
	
	Instance findOne(Long id);

}