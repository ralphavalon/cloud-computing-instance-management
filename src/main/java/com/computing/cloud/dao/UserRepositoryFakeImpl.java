package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.User.UserBuilder;

@Repository
public class UserRepositoryFakeImpl implements UserRepository {
	
	List<User> users = new ArrayList<User>();
	
	public UserRepositoryFakeImpl() {
		UserBuilder builder = User.builder();
		builder
			.username("userOne")
			.password("passOne");
		final User userOne = builder.build();
		
		builder = User.builder();
		builder
			.username("userTwo")
			.password("passTwo");
		final User userTwo = builder.build();
		
		users.add(userOne);
		users.add(userTwo);
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