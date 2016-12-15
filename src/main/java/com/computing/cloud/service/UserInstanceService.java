package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.to.request.CreateUserInstanceRequestTO;

public interface UserInstanceService {
	
	List<UserInstance> createInstances(CreateUserInstanceRequestTO createUserInstanceTO, int quantity);
	
	List<UserInstance> createInstances(UserInstance userInstance, int quantity);
	
	List<UserInstance> getInstancesByUser(Long userId);
	
	List<UserInstance> getInstancesByUserAndStatus(User user, InstanceStatus status);
	
	UserInstance findById(Long id);

}