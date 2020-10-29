package com.keiken.kenonuserinterface.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.DatewiseUserHandler;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;


@Repository
public interface RepoUserUpdatedTime extends CrudRepository<DatewiseUserHandler, String>{

	
	@Query("From DatewiseUserHandler where Date(lastUsedTime)=?1")
	List<DatewiseUserHandler> findEmailListUsedToday(Date currentDate);
	
	

}
