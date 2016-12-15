package com.computing.cloud.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.enums.Operation;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter 
@Builder
@Entity
public class History {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	private UserInstance userInstance;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	@Enumerated(EnumType.STRING)
	private Operation operation;
	
}