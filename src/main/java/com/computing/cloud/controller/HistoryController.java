package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.domain.History;
import com.computing.cloud.service.HistoryService;
import com.computing.cloud.service.UserService;

@RestController
@RequestMapping(value="/histories")
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<History>> findAll(@PathVariable("id") String id) {
		List<History> histories = historyService.getHistoriesByUser(userService.findById(Long.valueOf(id)));
		return new ResponseEntity<List<History>>(histories, HttpStatus.OK);
	}
	
}