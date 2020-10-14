package com.keiken.kenonuserinterface.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.model.UserRegisteredInfo;

@Repository
public interface ImpUserLoginOperation extends CrudRepository<UserRegisteredInfo, String>{
	//Invite with token;


}
