package com.computing.cloud.domain;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class PlanStorageType {
	
	private Long id;
	private Plan plan;
	private StorageType storageType;
	private BigDecimal weight;
	
	public PlanStorageType(Plan plan, StorageType storageType, BigDecimal weight) {
		this.plan = plan;
		this.storageType = storageType;
		this.weight = weight;
	}

}