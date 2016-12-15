package com.computing.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.OperatingSystemRepository;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.service.OperatingSystemService;

@Service
@Transactional
public class OperatingSystemServiceImpl implements OperatingSystemService {

	@Autowired
	private OperatingSystemRepository operatingSystemRepository;
	
	@Override
	public OperatingSystem findById(Long id) {
		return operatingSystemRepository.findOne(id);
	}

}