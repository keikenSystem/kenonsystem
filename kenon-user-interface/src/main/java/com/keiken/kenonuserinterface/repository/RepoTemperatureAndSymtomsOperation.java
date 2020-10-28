package com.keiken.kenonuserinterface.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;

@Repository
public interface RepoTemperatureAndSymtomsOperation extends CrudRepository<TemperatureAndSymtomsMesurement, String>{
	//check by date
	//insert data

	@Query("From TemperatureAndSymtomsMesurement where Date(lastUsedTime)=?1")
	List<TemperatureAndSymtomsMesurement> findEmailListUsedToday(Date currentDate);
	

	

}
