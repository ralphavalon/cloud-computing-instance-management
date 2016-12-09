package com.computing.cloud.domain;

import java.util.Calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.computing.cloud.enums.Operation;

@Getter @Builder
public class History {
	
	@Setter
	private Long id;
	private User user;
	private UserInstance userInstance;
	private Calendar lastUpdated;
	private Operation operation;
	
}