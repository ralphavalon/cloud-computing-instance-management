package com.computing.cloud.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.Plan;
import com.computing.cloud.domain.PlanStorageType;
import com.computing.cloud.domain.StorageType;
import com.computing.cloud.domain.Plan.PlanBuilder;

@Repository
public class PlanRepositoryFakeImpl implements PlanRepository {
	
	List<Plan> plans = new ArrayList<Plan>();
	private StorageType hdd = new StorageType("HDD");
	private StorageType ssd = new StorageType("SSD");
	
	public PlanRepositoryFakeImpl() {
		hdd.setId(1L);
		ssd.setId(2L);
		
		PlanBuilder builder = Plan.builder();
		builder
			.id(1L)
			.name("Basic")
			.pricePerCpu(new BigDecimal(0.004000))
			.pricePerMemory(new BigDecimal(0.006000))
			.pricePerStorage(new BigDecimal(0.000200));
		final Plan planOne = builder.build();
		
		planOne.setPlanStorageType(Arrays.asList(
				new PlanStorageType(planOne, hdd, new BigDecimal(1)),
				new PlanStorageType(planOne, ssd, new BigDecimal(3))
				));
		
		plans.add(planOne);
	}
	
	@Override
	public Plan findOne(Long id) {
		for (Plan plan : plans) {
			if(plan.getId().equals(id)) {
				return plan;
			}
		}
		return null;
	}

	@Override
	public List<Plan> findAll() {
		return Collections.unmodifiableList(plans);
	}

}