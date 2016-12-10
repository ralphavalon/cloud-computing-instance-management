package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;

import com.computing.cloud.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsernameAndPassword(String username, String password);
	
}