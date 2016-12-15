package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.Instance;

public interface InstanceService {
	
	Instance findById(Long id);
	
	List<Instance> findAll();

}