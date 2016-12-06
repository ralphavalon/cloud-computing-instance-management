package com.computing.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Service
public class UserInstanceServiceImpl implements UserInstanceService {

	@Override
	public List<UserInstance> createInstances(int quantity, InstanceStatus status) {
		List<UserInstance> instances = new ArrayList<UserInstance>();
		for (int i = 0; i < quantity; i++) {
			instances.add(new UserInstance(status));
		}
		return instances;
	}

}
