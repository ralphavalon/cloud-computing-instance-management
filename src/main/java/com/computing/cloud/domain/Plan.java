package com.computing.cloud.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
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
import lombok.Setter;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter @Builder
@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal pricePerCpu;
	private BigDecimal pricePerMemory;
	private BigDecimal pricePerStorage;
	@Setter
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PlanStorageType> planStorageType;
	
}