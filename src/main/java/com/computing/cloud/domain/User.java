package com.computing.cloud.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @Builder
@EqualsAndHashCode
public class User {

	private Long id;
	private String username;
	private String password;
	private String email;
	private String creditCard;
	
}