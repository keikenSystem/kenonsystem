package com.keiken.kenonuserinterface.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.model.RegistrationInfo;

@Repository
public interface RepoUser extends CrudRepository<EmployeeInfo, String> {

	

}
