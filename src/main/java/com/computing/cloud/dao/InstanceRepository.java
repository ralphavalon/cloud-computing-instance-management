package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;

import com.computing.cloud.domain.Instance;

public interface InstanceRepository extends CrudRepository<Instance, Long> {
	
}