package com.computing.cloud.service;

import com.computing.cloud.domain.OperatingSystem;

public interface OperatingSystemService {
	
	OperatingSystem findById(Long id);
	
}