package com.computing.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.User;
import com.computing.cloud.service.UserService;
import com.computing.cloud.utils.UserCopyProperties;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User update(Long id, User user) {
		final User savedUser = userRepository.findOne(id);
		
		UserCopyProperties.copy(user, savedUser);

		return userRepository.save(savedUser);
	}

	@Override
	public User findByExternalId(String externalId) {
		return userRepository.findByExternalId(externalId);
	}

}