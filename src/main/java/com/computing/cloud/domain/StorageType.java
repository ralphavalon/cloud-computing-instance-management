package com.computing.cloud.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class StorageType {
	
	private Long id;
	private String name;
	
	public StorageType(String name) {
		this.name = name;
	}

}