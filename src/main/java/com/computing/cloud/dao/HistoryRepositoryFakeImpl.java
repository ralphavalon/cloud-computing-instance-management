package com.computing.cloud.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.computing.cloud.domain.History;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.utils.DateHelper;

@Repository
public class HistoryRepositoryFakeImpl implements HistoryRepository {
	
	List<History> allHistories = new ArrayList<History>();
	
	public HistoryRepositoryFakeImpl() {
		allHistories = new ArrayList<History>();
	}
	static long i = 0L;
	
	@Override
	public History save(History history) {
		history.setId(++i);
		allHistories.add(history);
		return history;
	}
	
	@Override
	public List<History> findByUser(User user) {
		List<History> histories = new ArrayList<History>();
		for (History history : allHistories) {
			if(history.getUser().equals(user)) {
				histories.add(history);
			}
		}
		return histories;
	}
	
	@Override
	public List<History> findByUserInstance(UserInstance userInstance) {
		List<History> histories = new ArrayList<History>();
		for (History history : allHistories) {
			if(history.getUserInstance().equals(userInstance)) {
				histories.add(history);
			}
		}
		return histories;
	}
	
	@Override
	public List<History> findByUserInPeriod(User user, Date startDate, Date endDate) {
		List<History> histories = new ArrayList<History>();
		for (History history : allHistories) {
			if(history.getUser().equals(user) && between(history, startDate, endDate)) {
				histories.add(history);
			}
		}
		return histories;
	}

	private boolean between(History history, Date startDate, Date endDate) {
		final Date historyDate = history.getLastUpdated().getTime();
		return DateHelper.isBetween(historyDate, startDate, endDate);
	}
	
}