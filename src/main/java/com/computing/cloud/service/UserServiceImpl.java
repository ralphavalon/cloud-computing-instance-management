package com.computing.cloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.Authentication;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.AuthenticationException;
import com.computing.cloud.utils.UserCopyProperties;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication login(String username, String password)
			throws AuthenticationException {
		final User user = userRepository.findByUsernameAndPassword(username,
				password);
		if (user == null) {
			throw new AuthenticationException();
		}
		return new Authentication("token");
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

}