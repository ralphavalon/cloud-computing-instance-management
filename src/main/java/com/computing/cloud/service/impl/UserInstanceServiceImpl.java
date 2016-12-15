package com.computing.cloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.InstanceRepository;
import com.computing.cloud.dao.OperatingSystemRepository;
import com.computing.cloud.dao.UserInstanceRepository;
import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.service.UserInstanceService;
import com.computing.cloud.to.request.CreateUserInstanceRequestTO;

@Service
@Transactional
public class UserInstanceServiceImpl implements UserInstanceService {
	
	@Autowired
	private UserInstanceRepository userInstanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstanceRepository instanceRepository;
	
	@Autowired
	private OperatingSystemRepository operatingSystemRepository;

	@Override
	public List<UserInstance> createInstances(UserInstance userInstance, int quantity) {
		User user = userInstance.getUser();
		InstanceStatus status = userInstance.getStatus();
		Instance instance = userInstance.getInstance();
		OperatingSystem operatingSystem = userInstance.getOperatingSystem();
		
		List<UserInstance> instances = new ArrayList<UserInstance>();
		for (int i = 0; i < quantity; i++) {
			UserInstance newUserInstance = new UserInstance(user, status, instance, operatingSystem);
			userInstanceRepository.save(newUserInstance);
			instances.add(newUserInstance);
		}
		return instances;
	}

	@Override
	public List<UserInstance> getInstancesByUser(Long userId) {
		final User user = userRepository.findOne(userId);
		return userInstanceRepository.findByUser(user);
	}

	@Override
	public List<UserInstance> getInstancesByUserAndStatus(User user, InstanceStatus status) {
		return userInstanceRepository.findByUserAndStatus(user, status);
	}

	@Override
	public UserInstance findById(Long id) {
		return userInstanceRepository.findOne(id);
	}

	@Override
	public List<UserInstance> createInstances(CreateUserInstanceRequestTO createUserInstanceTO, int quantity) {
		final Instance instance = instanceRepository.findOne( createUserInstanceTO.getInstanceId() );
		final OperatingSystem operatingSystem = operatingSystemRepository.findOne( createUserInstanceTO.getOperatingSystemId() );
		final User user = userRepository.findOne( createUserInstanceTO.getUserId() );
		return createInstances(createUserInstanceTO.toDomain(user, instance, operatingSystem), quantity);
	}

}
