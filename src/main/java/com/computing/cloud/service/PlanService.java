package com.computing.cloud.service;

import java.math.BigDecimal;
import java.util.List;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.Plan;

public interface PlanService {
	
	Plan findById(Long id);
	
	List<Plan> findAll();
	
	BigDecimal getPricePerHour(Plan plan, Instance instance) throws Exception;

}