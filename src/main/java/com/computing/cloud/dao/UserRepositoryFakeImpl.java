package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.User.UserBuilder;

@Repository
public class UserRepositoryFakeImpl implements UserRepository {
	
	List<User> users = new ArrayList<User>();
	private static long i = 0L;
	
	public UserRepositoryFakeImpl() {
		UserBuilder builder = User.builder();
		builder
			.id(++i)
			.username("userOne")
			.password("passOne");
		final User userOne = builder.build();
		
		builder = User.builder();
		builder
			.id(++i)
			.username("userTwo")
			.password("passTwo");
		final User userTwo = builder.build();
		
		users.add(userOne);
		users.add(userTwo);
	}
	
	@Override
	public User save(User user) {
		user = User.builder()
			.id(++i)
			.username(user.getUsername())
			.password(user.getPassword())
			.email(user.getEmail())
			.creditCard(user.getCreditCard())
			.build();
		users.add(user);
		return user;
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