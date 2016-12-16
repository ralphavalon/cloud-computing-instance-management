package com.computing.cloud.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.User;
import com.computing.cloud.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsernameAndPasswordAndStatus(String username, String password, Boolean status) {
		return userRepository.findByUsernameAndPasswordAndStatus(username, password, status);
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
		
		if(StringUtils.isNotBlank(user.getCreditCard())) {
			savedUser.setCreditCard(user.getCreditCard());
		}
		if(StringUtils.isNotBlank(user.getEmail())) {
			savedUser.setEmail(user.getEmail());
		}
		if(StringUtils.isNotBlank(user.getUsername())) {
			savedUser.setUsername(user.getUsername());
		}
		savedUser.setStatus(user.getStatus());
		
		return userRepository.save(savedUser);
	}

	@Override
	public User findByExternalId(String externalId) {
		return userRepository.findByExternalId(externalId);
	}

}