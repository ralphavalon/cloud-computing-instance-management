package com.computing.cloud.dao;

import java.util.List;

import com.computing.cloud.domain.StorageType;

public interface StorageTypeRepository {
	
	StorageType findOne(Long id);
	
	List<StorageType> findAll();

}