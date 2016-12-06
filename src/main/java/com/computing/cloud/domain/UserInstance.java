package com.computing.cloud.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import com.computing.cloud.enums.InstanceStatus;

@Getter
public class UserInstance {

	@Setter
	private Long id;
	private User user;
	private String instanceUniqueName;
	private InstanceStatus status;
	
	public UserInstance(User user, InstanceStatus status) {
		this.user = user;
		this.instanceUniqueName = UUID.randomUUID().toString();
		this.status = status;
	}

}