package com.computing.cloud.service;

import java.util.List;

import com.computing.cloud.domain.Authentication;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.AuthenticationException;

public interface UserService {
	
	Authentication login(String username, String password) throws AuthenticationException;
	
	User create(User user);
	
	User update(Long id, User user);
	
	User findById(Long id);
	
	List<User> findAll();

}