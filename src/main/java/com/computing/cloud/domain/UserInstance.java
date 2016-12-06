package com.computing.cloud.domain;

import java.util.UUID;

import lombok.Getter;

public class UserInstance {
	
	@Getter
	private String instanceUniqueName;
	
	public UserInstance() {
		this.instanceUniqueName = UUID.randomUUID().toString();
	}

}