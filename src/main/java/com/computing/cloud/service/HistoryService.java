package com.computing.cloud.service;

import java.util.Date;
import java.util.List;

import com.computing.cloud.domain.History;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;

public interface HistoryService {
	
	History saveHistory(History history);
	
	List<History> getHistoriesByUser(User user);
	
	List<History> getHistoriesByUserInstance(UserInstance userInstance);

	List<History> getHistoriesByUserInPeriod(User user, Date startDate, Date endDate);
	
}