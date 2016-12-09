package com.computing.cloud.dao;

import java.util.Date;
import java.util.List;

import com.computing.cloud.domain.History;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;

public interface HistoryRepository {
	
	History save(History history);
	
	List<History> findByUser(User user);
	
	List<History> findByUserInstance(UserInstance userInstance);
	
	List<History> findByUserInPeriod(User user, Date startDate, Date endDate);

}