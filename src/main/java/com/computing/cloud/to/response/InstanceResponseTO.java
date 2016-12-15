package com.computing.cloud.to.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.Plan;
import com.computing.cloud.domain.StorageType;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class InstanceResponseTO {
	
	private Long id;
	private String name;
	private Long planId;
	private String planName;
	private Integer cpu;
	private Integer memory; // GB
	private Integer storage; // GB
	private StorageType storageType;

	public InstanceResponseTO(Instance instance) {
		this.id = instance.getId();
		this.name = instance.getName();
		
		final Plan plan = instance.getPlan();
		this.planId = plan.getId();
		this.planName = plan.getName();
		
		this.cpu = instance.getCpu();
		this.memory = instance.getMemory();
		this.storage = instance.getStorage();
		this.storageType = instance.getStorageType();
	}


	public static List<InstanceResponseTO> toTOList(List<Instance> entityList) {
		List<InstanceResponseTO> toList = new ArrayList<InstanceResponseTO>();
		for (Instance entity : entityList) {
			toList.add(new InstanceResponseTO(entity));
		}
		return toList;
	}

}
