package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

public interface UserInstanceService {
	
	List<UserInstance> createInstances(int quantity, InstanceStatus status);

}