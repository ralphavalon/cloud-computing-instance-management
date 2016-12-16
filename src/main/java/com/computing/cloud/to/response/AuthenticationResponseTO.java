package com.computing.cloud.to.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class AuthenticationResponseTO {

	private String token;
	
	public AuthenticationResponseTO(String token) {
		this.token = token;
	}
	
}