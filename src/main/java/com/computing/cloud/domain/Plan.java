package com.computing.cloud.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder
public class Plan {
	
	private Long id;
	private String name;
	private BigDecimal pricePerCpu;
	private BigDecimal pricePerMemory;
	private BigDecimal pricePerStorage;
	@Setter
	private List<PlanStorageType> planStorageType;

}