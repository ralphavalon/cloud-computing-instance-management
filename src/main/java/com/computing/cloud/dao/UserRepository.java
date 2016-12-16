package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByExternalId(String externalId);
	
}