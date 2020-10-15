package com.keiken.kenonuserinterface.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;

@Repository
public interface RepoTemperatureAndSymtomsOperation extends CrudRepository<TemperatureAndSymtomsMesurement, String>{
	//check by date
	//insert data
	

}
