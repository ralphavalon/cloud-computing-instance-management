package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.service.OperatingSystemService;

@RestController
@RequestMapping(value="/operatingSystems")
public class OperatingSystemController {
	
	@Autowired
	private OperatingSystemService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<OperatingSystem>> findAll() {
		List<OperatingSystem> operatingSystems = service.findAll();
		return new ResponseEntity<List<OperatingSystem>>(operatingSystems, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OperatingSystem> findByExternalId(@PathVariable("id") String id) {
		OperatingSystem operatingSystem = service.findById(Long.valueOf(id));
		return new ResponseEntity<OperatingSystem>(operatingSystem, HttpStatus.OK);
	}

}