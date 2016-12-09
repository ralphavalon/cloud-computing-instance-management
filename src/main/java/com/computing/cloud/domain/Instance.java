package com.computing.cloud.domain;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class Instance {

	private Long id;
	private String name;
	private Plan plan;
	private Integer cpu;
	private Integer memory; // GB
	private Integer storage; // GB
	private StorageType storageType;
	
}