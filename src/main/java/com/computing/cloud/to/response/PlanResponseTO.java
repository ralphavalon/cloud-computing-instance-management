package com.computing.cloud.to.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.Plan;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class PlanResponseTO {

	private Long id;
	private String name;
	
	public PlanResponseTO(Plan plan) {
		this.id = plan.getId();
		this.name = plan.getName();
	}
	
	public static List<PlanResponseTO> toTOList(List<Plan> entityList) {
		List<PlanResponseTO> toList = new ArrayList<PlanResponseTO>();
		for (Plan entity : entityList) {
			toList.add(new PlanResponseTO(entity));
		}
		return toList;
	}
	
}