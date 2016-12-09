package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

public interface UserInstanceService {
	
	List<UserInstance> createInstances(UserInstance userInstance, int quantity);
	
	List<UserInstance> getInstancesByUser(User user);
	
	List<UserInstance> getInstancesByUserAndStatus(User user, InstanceStatus status);

}