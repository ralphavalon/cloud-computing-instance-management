package com.computing.cloud.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.HistoryRepository;
import com.computing.cloud.domain.History;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public History saveHistory(History history) {
		return historyRepository.save(history);
	}

	@Override
	public List<History> getHistoriesByUser(User user) {
		return historyRepository.findByUser(user);
	}

	@Override
	public List<History> getHistoriesByUserInstance(UserInstance userInstance) {
		return historyRepository.findByUserInstance(userInstance);
	}

	@Override
	public List<History> getHistoriesByUserInPeriod(User user, Date startDate, Date endDate) {
		return historyRepository.findByUserInPeriod(user, startDate, endDate);
	}

}
