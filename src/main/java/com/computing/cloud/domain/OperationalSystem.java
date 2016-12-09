package com.computing.cloud.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class OperationalSystem {
	
	private Long id;
	private String name;
	
	public OperationalSystem(String name) {
		this.name = name;
	}

}