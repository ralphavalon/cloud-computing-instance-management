package com.computing.cloud.utils;

import com.computing.cloud.domain.User;

public class UserCopyProperties extends DomainCopyProperties {
	
	private UserCopyProperties() throws InstantiationException {
	    throw new InstantiationException("Instances of this type are forbidden.");
	}

	public static void copy(User source, User target) {
		copyProperties(source, target, new String[]{ "email", "username", "creditCard", "status" });
	}

}