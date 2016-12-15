package com.computing.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.computing.cloud.domain.User;
import com.computing.cloud.service.HistoryService;
import com.computing.cloud.service.UserService;
import com.computing.cloud.to.response.HistoryResponseTO;

@RestController
@RequestMapping(value="/histories")
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<HistoryResponseTO>> findAll(@PathVariable("id") String id) {
		final User user = userService.findById(Long.valueOf(id));
		List<HistoryResponseTO> histories = HistoryResponseTO.toTOList(historyService.getHistoriesByUser(user));
		return new ResponseEntity<List<HistoryResponseTO>>(histories, HttpStatus.OK);
	}
	
}