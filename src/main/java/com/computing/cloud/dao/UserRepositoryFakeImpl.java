package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;

@Repository
public class UserRepositoryFakeImpl implements UserRepository {
	
	List<User> users = new ArrayList<User>();
	
	public UserRepositoryFakeImpl() {
		final User userOne = new User();
		userOne.setUsername("userOne");
		userOne.setPassword("passOne");
		
		final User userTwo = new User();
		userTwo.setUsername("userTwo");
		userTwo.setPassword("passTwo");
		
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