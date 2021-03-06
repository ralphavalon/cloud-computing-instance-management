package com.computing.cloud.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Repository
public interface UserInstanceRepository extends CrudRepository<UserInstance, Long> {
	
	List<UserInstance> findByUser(User user);
	
	List<UserInstance> findByUserAndStatus(User user, InstanceStatus status);

}