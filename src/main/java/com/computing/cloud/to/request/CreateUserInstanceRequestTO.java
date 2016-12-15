package com.computing.cloud.to.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Getter
@NoArgsConstructor
public class CreateUserInstanceRequestTO {

	private Long userId;
	private Long instanceId;
	private Long operatingSystemId;
	private InstanceStatus status;

	private int quantity;

	public UserInstance toDomain(User user, Instance instance, OperatingSystem operatingSystem) {
		return new UserInstance(user, getStatus(), instance, operatingSystem);
	}

}