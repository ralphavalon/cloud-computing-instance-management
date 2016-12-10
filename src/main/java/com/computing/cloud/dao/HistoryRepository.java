package com.computing.cloud.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.computing.cloud.domain.History;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;

public interface HistoryRepository extends CrudRepository<History, Long>{
	
	List<History> findByUser(User user);
	
	List<History> findByUserInstance(UserInstance userInstance);
	
	@Query("SELECT o FROM History o WHERE o.lastUpdated BETWEEN :from AND :to AND o.user = :user")
	List<History> findByUserInPeriod(
		@Param("user")	User user, 
	    @Param("from") @Temporal(TemporalType.TIMESTAMP) Date from,
	    @Param("to") @Temporal(TemporalType.TIMESTAMP) Date to
	);

}