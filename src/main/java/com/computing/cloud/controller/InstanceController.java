package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.service.InstanceService;
import com.computing.cloud.to.response.InstanceResponseTO;

@RestController
@RequestMapping(value="/instances")
public class InstanceController {
	
	@Autowired
	private InstanceService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<InstanceResponseTO>> findAll() {
		List<InstanceResponseTO> instances = InstanceResponseTO.toTOList(service.findAll());
		return new ResponseEntity<List<InstanceResponseTO>>(instances, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<InstanceResponseTO> findByExternalId(@PathVariable("id") String id) {
		InstanceResponseTO instance = new InstanceResponseTO( service.findById(Long.valueOf(id)) );
		return new ResponseEntity<InstanceResponseTO>(instance, HttpStatus.OK);
	}
	
}