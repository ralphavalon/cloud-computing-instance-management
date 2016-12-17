package com.computing.cloud.to.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class AuthenticationResponseTO {

	private String token;
	private String externalId;
	private Long userId;
	
	public AuthenticationResponseTO(String token, String externalId, Long userId) {
		this.token = token;
		this.externalId = externalId;
		this.userId = userId;
	}
	
}