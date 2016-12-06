package com.computing.cloud.dao;

import java.util.List;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

public interface UserInstanceRepository {
	
	UserInstance save(UserInstance userInstance);
	
	List<UserInstance> findByUser(User user);
	
	List<UserInstance> findByUserAndStatus(User user, InstanceStatus status);

}