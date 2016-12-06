package com.computing.cloud.service;

import java.util.Arrays;
import java.util.List;

import com.computing.cloud.domain.UserInstance;

public class UserInstanceServiceImpl implements UserInstanceService {

	@Override
	public List<UserInstance> createInstances(int quantity) {
		return Arrays.asList(new UserInstance(), new UserInstance(), new UserInstance(), new UserInstance(), new UserInstance());
	}

}
