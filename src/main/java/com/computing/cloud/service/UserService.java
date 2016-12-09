package com.computing.cloud.service;

import com.computing.cloud.domain.Authentication;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.AuthenticationException;

public interface UserService {
	
	Authentication login(String username, String password) throws AuthenticationException;
	
	User saveUser(User user);

}