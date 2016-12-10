package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.domain.Plan;
import com.computing.cloud.service.PlanService;

@RestController
@RequestMapping(value="/plans")
public class PlanController {
	
	@Autowired
	private PlanService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Plan>> findAll() {
		List<Plan> plans = service.findAll();
		return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Plan> findByExternalId(@PathVariable("id") String id) {
		Plan plan = service.findById(Long.valueOf(id));
		return new ResponseEntity<Plan>(plan, HttpStatus.OK);
	}

}