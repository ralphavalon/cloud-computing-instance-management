package com.computing.cloud.to.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.User.UserBuilder;

@Getter
@NoArgsConstructor
public class UpdateUserInstanceRequestTO extends UserRequestTO {
	
	private Boolean status;
	
	public User toDomain() {
		UserBuilder builder = User.builder()
				.creditCard(getCreditCard())
				.email(getEmail())
				.username(getUsername())
				.status(getStatus());
		return builder.build();
	}

}