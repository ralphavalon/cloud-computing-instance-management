package com.computing.cloud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {
	
}