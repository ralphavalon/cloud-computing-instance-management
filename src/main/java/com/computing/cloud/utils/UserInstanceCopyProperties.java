package com.computing.cloud.utils;

import com.computing.cloud.domain.UserInstance;

public class UserInstanceCopyProperties extends DomainCopyProperties {
	
	private UserInstanceCopyProperties() throws InstantiationException {
	    throw new InstantiationException("Instances of this type are forbidden.");
	}

	public static void copy(UserInstance source, UserInstance target) {
		copyProperties(source, target, new String[]{ "status" });
	}

}