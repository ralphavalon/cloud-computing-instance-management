package com.computing.cloud.to.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Getter
@NoArgsConstructor
public class UpdateUserInstanceRequestTO {

	private InstanceStatus status;

	public UserInstance toDomain() {
		return new UserInstance(null, getStatus(), null, null);
	}

}