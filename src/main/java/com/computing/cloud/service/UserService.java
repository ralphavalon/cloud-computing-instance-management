package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.User;

public interface UserService {
	
	User findByUsernameAndPasswordAndStatus(String username, String password, Boolean status);
	
	User create(User user);
	
	User update(Long id, User user);
	
	User findById(Long id);
	
	List<User> findAll();
	
	User findByExternalId(String externalId);

}