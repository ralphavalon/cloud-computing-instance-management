package com.computing.cloud.domain;

import lombok.Builder;
import lombok.Getter;

import com.computing.cloud.enums.StorageType;

@Getter @Builder
public class Instance {

	private Long id;
	private String name;
	private Integer cpu;
	private Integer memory; // GB
	private Integer storage; // GB
	private StorageType storageType;
	
}