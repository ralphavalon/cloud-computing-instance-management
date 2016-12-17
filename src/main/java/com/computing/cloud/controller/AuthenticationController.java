package com.computing.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.authentication.Encrypter;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.NotFoundException;
import com.computing.cloud.service.UserService;
import com.computing.cloud.to.request.AuthenticationRequestTO;
import com.computing.cloud.to.response.AuthenticationResponseTO;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<AuthenticationResponseTO> login(@RequestBody AuthenticationRequestTO request) {
		User user = service.findByUsernameAndPasswordAndStatus(request.getUsername(), request.getPassword(), Boolean.TRUE);
		if(user == null) {
			throw new NotFoundException(User.class);
		}
		String token = Encrypter.encryptToken( user.getExternalId() );
		return new ResponseEntity<AuthenticationResponseTO>(new AuthenticationResponseTO(token, user.getExternalId(), user.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<AuthenticationResponseTO> logout() {
		return new ResponseEntity<AuthenticationResponseTO>(HttpStatus.NO_CONTENT);
	}

}
