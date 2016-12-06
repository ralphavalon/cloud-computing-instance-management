package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;

@Repository
public class UserRepositoryFakeImpl implements UserRepository {
	
	static List<User> users = new ArrayList<User>();
	static {
		final User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		users.add(user);
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		for (User user : users) {
			if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				return user;
			}
		}
		return null;
	}

}