package com.computing.cloud.to.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.computing.cloud.domain.History;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.Operation;
import com.computing.cloud.utils.DateHelper;

@Getter
@NoArgsConstructor(access=AccessLevel.PACKAGE)
public class HistoryResponseTO {

	private Long id;
	private Long userId;
	private String userName;
	private Long userInstanceId;
	private String userInstanceName;
	private String lastUpdated;
	private Operation operation;
	
	public HistoryResponseTO(History history) {
		this.id = history.getId();
		final User user = history.getUser();
		final UserInstance userInstance = history.getUserInstance();
		
		this.userInstanceId = userInstance.getId();
		this.userInstanceName = userInstance.getInstanceUniqueName();
		this.userId = user.getId();
		this.userName = user.getUsername();
		final Date datetime = history.getLastUpdated().getTime();
		this.lastUpdated = DateHelper.fromDateToString(datetime, "YYYY-MM-dd HH:mm:ss");
		this.operation = history.getOperation();
	}
	
	public static List<HistoryResponseTO> toTOList(List<History> entityList) {
		List<HistoryResponseTO> toList = new ArrayList<HistoryResponseTO>();
		for (History entity : entityList) {
			toList.add(new HistoryResponseTO(entity));
		}
		return toList;
	}
	
}