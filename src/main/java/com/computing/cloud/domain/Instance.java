package com.computing.cloud.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter @Builder
@Entity
public class Instance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToOne(fetch=FetchType.EAGER)
	private Plan plan;
	private Integer cpu;
	private Integer memory; // GB
	private Integer storage; // GB
	@ManyToOne(fetch=FetchType.EAGER)
	private StorageType storageType;
	
}