package com.computing.cloud.to.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationRequestTO {

	private String username;
	private String password;

}