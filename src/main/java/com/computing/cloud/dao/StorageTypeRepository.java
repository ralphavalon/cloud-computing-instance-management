package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;

import com.computing.cloud.domain.StorageType;

public interface StorageTypeRepository extends CrudRepository<StorageType, Long>{
	
}