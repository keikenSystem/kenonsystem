package com.keiken.kenonuserinterface.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;

public interface RepoLogger extends CrudRepository<TemperatureAndSymtomsMesurement, Integer>{
	
	@Query("From TemperatureAndSymtomsMesurement where Date(lastUsedTime)=?1")
	public List<TemperatureAndSymtomsMesurement> getListByDate(Date selectedDate);

}
