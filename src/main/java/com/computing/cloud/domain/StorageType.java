package com.computing.cloud.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class StorageType {
	
	@Setter
	private Long id;
	private String name;
	
	public StorageType(String name) {
		this.name = name;
	}

}