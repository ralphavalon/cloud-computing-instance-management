package com.computing.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.InstanceRepository;
import com.computing.cloud.domain.Instance;

@Service
@Transactional
public class InstanceServiceImpl implements InstanceService {

	@Autowired
	private InstanceRepository instanceRepository;
	
	@Override
	public Instance findById(Long id) {
		return instanceRepository.findOne(id);
	}
	
}