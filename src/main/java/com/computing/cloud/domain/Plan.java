package com.computing.cloud.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter @Builder
@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(precision = 19, scale = 6)
	private BigDecimal pricePerCpu;
	@Column(precision = 19, scale = 6)
	private BigDecimal pricePerMemory;
	@Column(precision = 19, scale = 6)
	private BigDecimal pricePerStorage;
	
	@OneToMany(mappedBy="plan", fetch=FetchType.EAGER)
	private Set<PlanStorageType> planStorageType;
	
}