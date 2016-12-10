package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.StorageType;

@Repository
public interface StorageTypeRepository extends CrudRepository<StorageType, Long>{
	
}