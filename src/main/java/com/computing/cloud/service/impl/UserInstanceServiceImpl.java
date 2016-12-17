package com.computing.cloud.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.dao.HistoryRepository;
import com.computing.cloud.dao.InstanceRepository;
import com.computing.cloud.dao.OperatingSystemRepository;
import com.computing.cloud.dao.UserInstanceRepository;
import com.computing.cloud.dao.UserRepository;
import com.computing.cloud.domain.History;
import com.computing.cloud.domain.History.HistoryBuilder;
import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.enums.Operation;
import com.computing.cloud.service.UserInstanceService;
import com.computing.cloud.to.request.CreateUserInstanceRequestTO;
import com.computing.cloud.to.request.UpdateUserInstanceRequestTO;

@Service
@Transactional
public class UserInstanceServiceImpl implements UserInstanceService {
	
	@Autowired
	private UserInstanceRepository userInstanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstanceRepository instanceRepository;
	
	@Autowired
	private OperatingSystemRepository operatingSystemRepository;
	
	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public List<UserInstance> createInstances(UserInstance userInstance, int quantity) {
		final User user = userInstance.getUser();
		final InstanceStatus status = userInstance.getStatus();
		final Instance instance = userInstance.getInstance();
		final OperatingSystem operatingSystem = userInstance.getOperatingSystem();
		
		List<UserInstance> instances = new ArrayList<UserInstance>();
		for (int i = 0; i < quantity; i++) {
			UserInstance newUserInstance = new UserInstance(user, status, instance, operatingSystem);
			userInstanceRepository.save(newUserInstance);
			instances.add(newUserInstance);
			
			if(InstanceStatus.ON.equals(newUserInstance.getStatus())) {
				HistoryBuilder builder = History.builder();
					History history = builder
						.operation(Operation.TURN_ON)
						.user(newUserInstance.getUser())
						.userInstance(newUserInstance)
						.lastUpdated(Calendar.getInstance())
						.build();
					historyRepository.save(history);
			}
		}
		return instances;
	}

	@Override
	public List<UserInstance> getInstancesByUser(Long userId) {
		final User user = userRepository.findOne(userId);
		return userInstanceRepository.findByUser(user);
	}

	@Override
	public List<UserInstance> getInstancesByUserAndStatus(User user, InstanceStatus status) {
		return userInstanceRepository.findByUserAndStatus(user, status);
	}

	@Override
	public UserInstance findById(Long id) {
		return userInstanceRepository.findOne(id);
	}

	@Override
	public List<UserInstance> createInstances(CreateUserInstanceRequestTO createUserInstanceTO, int quantity) {
		return createInstances(getUserInstanceByRequest(createUserInstanceTO), quantity);
	}

	private UserInstance getUserInstanceByRequest(CreateUserInstanceRequestTO createUserInstanceTO) {
		final Instance instance = instanceRepository.findOne( createUserInstanceTO.getInstanceId() );
		final OperatingSystem operatingSystem = operatingSystemRepository.findOne( createUserInstanceTO.getOperatingSystemId() );
		final User user = userRepository.findOne( createUserInstanceTO.getUserId() );
		return createUserInstanceTO.toDomain(user, instance, operatingSystem);
	}

	@Override
	public UserInstance update(Long id, UpdateUserInstanceRequestTO updateUserInstanceTO) {
		final UserInstance savedUserInstance = userInstanceRepository.findOne(id);
		
		savedUserInstance.setStatus(updateUserInstanceTO.getStatus());
		HistoryBuilder builder = History.builder();
		Operation operation = InstanceStatus.ON.equals(savedUserInstance.getStatus()) ? Operation.TURN_ON : Operation.TURN_OFF;
		History history = builder
			.operation(operation)
			.user(savedUserInstance.getUser())
			.userInstance(savedUserInstance)
			.lastUpdated(Calendar.getInstance())
			.build();
		historyRepository.save(history);

		return userInstanceRepository.save(savedUserInstance);
	}

}