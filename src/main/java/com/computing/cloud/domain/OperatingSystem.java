package com.computing.cloud.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@Entity
public class OperatingSystem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter
	private Long id;
	private String name;
	
	public OperatingSystem(String name) {
		this.name = name;
	}

}