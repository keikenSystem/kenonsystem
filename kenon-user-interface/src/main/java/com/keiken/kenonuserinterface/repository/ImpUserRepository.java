package com.keiken.kenonuserinterface.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.model.UserRegisteredInfo;

@Repository
public interface ImpUserRepository extends CrudRepository<EmployeeInfo, String> {

	

}
