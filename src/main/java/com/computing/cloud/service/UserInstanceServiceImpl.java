package com.computing.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.UserInstanceRepository;
import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Service
@Transactional
public class UserInstanceServiceImpl implements UserInstanceService {
	
	@Autowired
	private UserInstanceRepository userInstanceRepository;
	
	@Autowired
	private UserRepository userRepository;

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

}
