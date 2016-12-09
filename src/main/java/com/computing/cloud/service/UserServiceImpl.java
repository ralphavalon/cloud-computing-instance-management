package com.computing.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.Authentication;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Authentication login(String username, String password) throws AuthenticationException {
		final User user = userRepository.findByUsernameAndPassword(username, password);
		if(user == null) {
			throw new AuthenticationException();
		}
		return new Authentication("token");
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}