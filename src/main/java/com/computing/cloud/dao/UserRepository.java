package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsernameAndPasswordAndStatus(String username, String password, Boolean status);
	
	User findByExternalId(String externalId);
	
}