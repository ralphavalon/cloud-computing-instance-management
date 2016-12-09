package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.Instance.InstanceBuilder;
import com.computing.cloud.enums.StorageType;

@Repository
public class InstanceRepositoryFakeImpl implements InstanceRepository {
	
	List<Instance> instances = new ArrayList<Instance>();
	
	public InstanceRepositoryFakeImpl() {
		InstanceBuilder builder = Instance.builder();
		builder
			.id(1L)
			.name("small")
			.cpu(1)
			.memory(2)
			.storage(40)
			.storageType(StorageType.HDD);
		final Instance instanceOne = builder.build();
		
		builder = Instance.builder();
		builder
			.id(2L)
			.name("medium")
			.cpu(2)
			.memory(4)
			.storage(100)
			.storageType(StorageType.HDD);
		final Instance instanceTwo = builder.build();
		
		builder = Instance.builder();
		builder
			.id(3L)
			.name("large")
			.cpu(2)
			.memory(8)
			.storage(250)
			.storageType(StorageType.SSD);
		final Instance instanceThree = builder.build();
		
		instances.add(instanceOne);
		instances.add(instanceTwo);
		instances.add(instanceThree);
	}
	
	@Override
	public Instance findOne(Long id) {
		for (Instance instance : instances) {
			if(instance.getId().equals(id)) {
				return instance;
			}
		}
		return null;
	}

}