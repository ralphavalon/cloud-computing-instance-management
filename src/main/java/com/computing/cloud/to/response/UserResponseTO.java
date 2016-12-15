package com.computing.cloud.to.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.User;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class UserResponseTO {

	private Long id;
	private String user;
	private String email;
	
	public UserResponseTO(User user) {
		this.id = user.getId();
		this.user = user.getUsername();
		this.email = user.getEmail();
	}
	
	public static List<UserResponseTO> toTOList(List<User> entityList) {
		List<UserResponseTO> toList = new ArrayList<UserResponseTO>();
		for (User entity : entityList) {
			toList.add(new UserResponseTO(entity));
		}
		return toList;
	}
	
}