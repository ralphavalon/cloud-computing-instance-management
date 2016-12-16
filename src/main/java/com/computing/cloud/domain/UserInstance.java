package com.computing.cloud.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.computing.cloud.enums.InstanceStatus;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode(exclude={"status", "user", "operatingSystem", "instance"})
@Entity
public class UserInstance {

	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	private Instance instance;
	@ManyToOne(fetch=FetchType.EAGER)
	private OperatingSystem operatingSystem;
	
	private String instanceUniqueName;
	
	@Enumerated(EnumType.STRING)
	@Setter
	private InstanceStatus status;
	
	public UserInstance(User user, InstanceStatus status, Instance instance, OperatingSystem operatingSystem) {
		this.user = user;
		this.instanceUniqueName = UUID.randomUUID().toString();
		this.status = status;
		this.instance = instance;
		this.operatingSystem = operatingSystem;
	}

}