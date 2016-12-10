package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;

import com.computing.cloud.domain.Plan;

public interface PlanRepository extends CrudRepository<Plan, Long> {
	
}