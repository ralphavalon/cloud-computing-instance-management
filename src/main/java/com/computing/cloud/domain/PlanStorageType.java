package com.computing.cloud.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
@Entity
public class PlanStorageType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN) 
	private Plan plan;
	@ManyToOne(fetch=FetchType.EAGER)
	private StorageType storageType;
	@Column(precision = 19, scale = 6)
	private BigDecimal weight;
	
	public PlanStorageType(Plan plan, StorageType storageType, BigDecimal weight) {
		this.plan = plan;
		this.storageType = storageType;
		this.weight = weight;
	}

}