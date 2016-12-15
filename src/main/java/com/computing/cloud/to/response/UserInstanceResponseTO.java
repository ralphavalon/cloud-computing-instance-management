package com.computing.cloud.to.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class UserInstanceResponseTO {

	private Long id;
	private Long instanceId;
	private String instanceName;
	private String userInstanceName;
	private String operatingSystem;
	private InstanceStatus status;
	
	public UserInstanceResponseTO(UserInstance userInstance) {
		this.id = userInstance.getId();
		
		final Instance instance = userInstance.getInstance();
		this.instanceId = instance.getId();
		this.instanceName = instance.getName();
		
		this.userInstanceName = userInstance.getInstanceUniqueName();
		this.operatingSystem = userInstance.getOperatingSystem().getName();
		this.status = userInstance.getStatus();
	}
	
	public static List<UserInstanceResponseTO> toTOList(List<UserInstance> entityList) {
		List<UserInstanceResponseTO> toList = new ArrayList<UserInstanceResponseTO>();
		for (UserInstance entity : entityList) {
			toList.add(new UserInstanceResponseTO(entity));
		}
		return toList;
	}

}