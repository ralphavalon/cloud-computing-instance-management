package com.computing.cloud.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class OperatingSystem {
	
	private Long id;
	private String name;
	
	public OperatingSystem(String name) {
		this.name = name;
	}

}