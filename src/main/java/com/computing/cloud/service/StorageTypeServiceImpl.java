package com.computing.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computing.cloud.dao.StorageTypeRepository;
import com.computing.cloud.domain.StorageType;

@Service
public class StorageTypeServiceImpl implements StorageTypeService {

	@Autowired
	private StorageTypeRepository instanceRepository;
	
	@Override
	public StorageType findById(Long id) {
		return instanceRepository.findOne(id);
	}
	
}