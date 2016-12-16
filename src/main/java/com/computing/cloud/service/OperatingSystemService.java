package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.OperatingSystem;

public interface OperatingSystemService {
	
	List<OperatingSystem> findAll();
	
	OperatingSystem findById(Long id);
	
}