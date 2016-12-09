package com.computing.cloud.dao;

import java.util.List;

import com.computing.cloud.domain.Plan;

public interface PlanRepository {
	
	Plan findOne(Long id);
	
	List<Plan> findAll();

}