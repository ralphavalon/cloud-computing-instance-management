package com.computing.cloud.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.PlanRepository;
import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.Plan;
import com.computing.cloud.domain.PlanStorageType;
import com.computing.cloud.domain.StorageType;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRepository;
	
	@Override
	public Plan findById(Long id) {
		return planRepository.findOne(id);
	}

	@Override
	public List<Plan> findAll() {
		return planRepository.findAll();
	}

	@Override
	public BigDecimal getPricePerHour(Plan plan, Instance instance) throws Exception {
		StorageType type = instance.getStorageType();
		final PlanStorageType planStorageType = getPlanStorageType(plan, type);
		
		BigDecimal pricePerHour = add(
				plan.getPricePerCpu().multiply(new BigDecimal(instance.getCpu())),
				plan.getPricePerMemory().multiply(new BigDecimal(instance.getMemory())),
				plan.getPricePerStorage()
					.multiply( planStorageType.getWeight() )
					.multiply(new BigDecimal(instance.getStorage()))
				
				);
		return pricePerHour;
	}
	
	private PlanStorageType getPlanStorageType(Plan plan, StorageType storageType) throws Exception {
		final List<PlanStorageType> planStorageTypeList = plan.getPlanStorageType();
		for (PlanStorageType planStorageType : planStorageTypeList) {
			if(planStorageType.getStorageType().equals(storageType)) {
				return planStorageType;
			}
		}
		throw new Exception();
	}
	
	private BigDecimal add(BigDecimal... values) {
		BigDecimal result = BigDecimal.ZERO;
		for (BigDecimal value : values) {
			result = result.add(value);
		}
		return result;
	}
	
}