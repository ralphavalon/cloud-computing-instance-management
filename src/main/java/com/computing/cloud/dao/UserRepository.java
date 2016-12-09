package com.computing.cloud.dao;

import com.computing.cloud.domain.User;

public interface UserRepository {
	
	User findByUsernameAndPassword(String username, String password);
	
	User save(User user);

}