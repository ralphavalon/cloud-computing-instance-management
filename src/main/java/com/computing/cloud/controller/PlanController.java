package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.service.PlanService;
import com.computing.cloud.to.response.PlanResponseTO;

@RestController
@RequestMapping(value="/plans")
public class PlanController {
	
	@Autowired
	private PlanService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<PlanResponseTO>> findAll() {
		List<PlanResponseTO> plans = PlanResponseTO.toTOList(service.findAll());
		return new ResponseEntity<List<PlanResponseTO>>(plans, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PlanResponseTO> findByExternalId(@PathVariable("id") String id) {
		PlanResponseTO plan = new PlanResponseTO( service.findById(Long.valueOf(id)) );
		return new ResponseEntity<PlanResponseTO>(plan, HttpStatus.OK);
	}

}