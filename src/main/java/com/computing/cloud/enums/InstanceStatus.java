package com.computing.cloud.enums;

import lombok.Getter;

public enum InstanceStatus {
	
	ON(true),
	OFF(false);
	
	@Getter
	private boolean status;
	
	private InstanceStatus(boolean status) {
		this.status = status;
	}

}