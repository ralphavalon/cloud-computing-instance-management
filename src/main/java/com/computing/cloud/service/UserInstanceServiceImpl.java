package com.computing.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computing.cloud.dao.UserInstanceRepository;
import com.computing.cloud.domain.OperationalSystem;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Service
public class UserInstanceServiceImpl implements UserInstanceService {
	
	@Autowired
	private UserInstanceRepository userInstanceRepository;

	@Override
	public List<UserInstance> createInstances(User user, int quantity, InstanceStatus status, OperationalSystem operationalSystem) {
		List<UserInstance> instances = new ArrayList<UserInstance>();
		for (int i = 0; i < quantity; i++) {
			final UserInstance userInstance = new UserInstance(user, status, operationalSystem);
			userInstanceRepository.save(userInstance);
			instances.add(userInstance);
		}
		return instances;
	}

	@Override
	public List<UserInstance> getInstancesByUser(User user) {
		return userInstanceRepository.findByUser(user);
	}

	@Override
	public List<UserInstance> getInstancesByUserAndStatus(User user, InstanceStatus status) {
		return userInstanceRepository.findByUserAndStatus(user, status);
	}

}
