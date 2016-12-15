package com.computing.cloud.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@Entity
public class PlanStorageType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "plan_storage_id")
	private Long id;
	@ManyToOne	
	@JoinColumn(name = "plan_id")
	private Plan plan;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="storage_type_id")
	private StorageType storageType;
	
	@Column(precision = 19, scale = 6)
	private BigDecimal weight;
	
}