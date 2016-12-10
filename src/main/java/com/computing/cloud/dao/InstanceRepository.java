package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.Instance;

@Repository
public interface InstanceRepository extends CrudRepository<Instance, Long> {
	
}