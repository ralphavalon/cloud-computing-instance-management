package com.computing.cloud.domain;

import lombok.Getter;

@Getter
public class OperatingSystem {
	
	private Long id;
	private String name;
	
	public OperatingSystem(String name) {
		this.name = name;
	}

}