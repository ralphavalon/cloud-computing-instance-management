package com.computing.cloud.to.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.User.UserBuilder;

@Getter
@NoArgsConstructor
public class CreateUserRequestTO extends UserRequestTO {

	private String password;

	public User toDomain() {
		UserBuilder builder = User.builder()
				.creditCard(getCreditCard())
				.email(getEmail())
				.username(getUsername())
				.password(getPassword())
				.externalId(UUID.randomUUID().toString())
				.status(Boolean.TRUE);
		return builder.build();
	}

}