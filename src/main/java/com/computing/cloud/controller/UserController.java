package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.service.UserService;
import com.computing.cloud.to.response.UserResponseTO;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<UserResponseTO>> findAll() {
		List<UserResponseTO> users = UserResponseTO.toTOList(service.findAll());
		return new ResponseEntity<List<UserResponseTO>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserResponseTO> findByExternalId(@PathVariable("id") String id) {
		UserResponseTO user = new UserResponseTO( service.findById(Long.valueOf(id)) );
		return new ResponseEntity<UserResponseTO>(user, HttpStatus.OK);
	}

}