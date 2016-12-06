package com.computing.cloud.service;

import com.computing.cloud.domain.Authentication;
import com.computing.cloud.exception.AuthenticationException;

public class UserServiceImpl implements UserService {

	@Override
	public Authentication login(String username, String password) throws AuthenticationException {
		if("username".equals(username) && "password".equals(password)) {
			return new Authentication("token");
		}
		throw new AuthenticationException();
	}

}