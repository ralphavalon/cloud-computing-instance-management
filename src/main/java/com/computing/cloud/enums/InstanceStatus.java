package com.computing.cloud.enums;


public enum InstanceStatus {
	
	ON,
	OFF;
	
	public static InstanceStatus getByStatus(boolean status) {
		return status ? ON : OFF;
	}

}