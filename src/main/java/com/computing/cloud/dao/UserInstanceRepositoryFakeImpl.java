package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Repository
public class UserInstanceRepositoryFakeImpl implements UserInstanceRepository {
	
	List<UserInstance> allUsersInstances = new ArrayList<UserInstance>();
	
	public UserInstanceRepositoryFakeImpl() {
		allUsersInstances = new ArrayList<UserInstance>();
	}
	static long i = 0L;
	
	@Override
	public UserInstance save(UserInstance userInstance) {
		userInstance.setId(++i);
		allUsersInstances.add(userInstance);
		return userInstance;
	}
	
	@Override
	public List<UserInstance> findByUser(User user) {
		List<UserInstance> userInstances = new ArrayList<UserInstance>();
		for (UserInstance userInstance : allUsersInstances) {
			if(isSameUser(user, userInstance.getUser())) {
				userInstances.add(userInstance);
			}
		}
		return userInstances;
	}
	
	@Override
	public List<UserInstance> findByUserAndStatus(User user, InstanceStatus status) {
		List<UserInstance> userInstances = new ArrayList<UserInstance>();
		for (UserInstance userInstance : allUsersInstances) {
			if(isSameUser(user, userInstance.getUser()) && userInstance.getStatus().equals(status)) {
				userInstances.add(userInstance);
			}
		}
		return userInstances;
	}
	
	private boolean isSameUser(User user, User userInstance) {
		return user.getUsername().equals(userInstance.getUsername());
	}
	
}