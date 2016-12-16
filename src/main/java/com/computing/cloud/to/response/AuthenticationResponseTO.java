package com.computing.cloud.to.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class AuthenticationResponseTO {

	private String token;
	private String externalId;
	
	public AuthenticationResponseTO(String token, String externalId) {
		this.token = token;
		this.externalId = externalId;
	}
	
}