package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.UserInstance;

public interface UserInstanceService {
	
	List<UserInstance> createInstances(int quantity);

}