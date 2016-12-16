package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.authentication.Authenticate;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.service.UserInstanceService;
import com.computing.cloud.to.request.CreateUserInstanceRequestTO;
import com.computing.cloud.to.request.UpdateUserInstanceRequestTO;
import com.computing.cloud.to.response.UserInstanceResponseTO;

@RestController
@RequestMapping(value="/userInstances")
public class UserInstanceController {
	
	@Autowired
	private UserInstanceService userInstanceService;
	
	@Authenticate
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<UserInstanceResponseTO>> findAll(@PathVariable("id") String id) {
		List<UserInstanceResponseTO> userInstances = UserInstanceResponseTO.toTOList(userInstanceService.getInstancesByUser( Long.valueOf(id)) );
		return new ResponseEntity<List<UserInstanceResponseTO>>(userInstances, HttpStatus.OK);
	}
	
	@Authenticate
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserInstanceResponseTO> findByExternalId(@PathVariable("id") String id) {
		UserInstanceResponseTO userInstance = new UserInstanceResponseTO( userInstanceService.findById(Long.valueOf(id)) );
		return new ResponseEntity<UserInstanceResponseTO>(userInstance, HttpStatus.OK);
	}
	
	@Authenticate
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<List<UserInstanceResponseTO>> create(@RequestBody CreateUserInstanceRequestTO userInstanceRequestTO) {
		final List<UserInstance> createdUserInstances = userInstanceService.createInstances( userInstanceRequestTO, userInstanceRequestTO.getQuantity() );
		List<UserInstanceResponseTO> userInstances = UserInstanceResponseTO.toTOList( createdUserInstances );
		return new ResponseEntity<List<UserInstanceResponseTO>>(userInstances, HttpStatus.CREATED);
	}
	
	@Authenticate
	@RequestMapping(value="/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<UserInstanceResponseTO> update(@PathVariable("id") String id, @RequestBody UpdateUserInstanceRequestTO userInstanceRequestTO) {
		UserInstanceResponseTO userInstance = new UserInstanceResponseTO( userInstanceService.update( Long.valueOf(id), userInstanceRequestTO ) );
		return new ResponseEntity<UserInstanceResponseTO>(userInstance, HttpStatus.NO_CONTENT);
	}

}