package com.computing.cloud.domain;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class User {

	private String username;
	private String password;
	
}