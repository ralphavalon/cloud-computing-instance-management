package com.computing.cloud.to.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.User.UserBuilder;

@Getter
@NoArgsConstructor
public class CreateUserInstanceRequestTO extends UserRequestTO {

	private String password;

	public User toDomain() {
		UserBuilder builder = User.builder()
				.creditCard(getCreditCard())
				.email(getEmail())
				.username(getUsername())
				.password(getPassword())
				.status(Boolean.TRUE);
		return builder.build();
	}

}