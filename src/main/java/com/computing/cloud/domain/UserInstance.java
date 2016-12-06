package com.computing.cloud.domain;

import java.util.UUID;

import lombok.Getter;

import com.computing.cloud.enums.InstanceStatus;

public class UserInstance {
	
	@Getter
	private String instanceUniqueName;
	@Getter
	private InstanceStatus status;
	
	public UserInstance(InstanceStatus status) {
		this.instanceUniqueName = UUID.randomUUID().toString();
		this.status = status;
	}

}