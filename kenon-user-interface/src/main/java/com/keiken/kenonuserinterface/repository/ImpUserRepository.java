package com.keiken.kenonuserinterface.repository;

import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.UserRegisteredInfo;

@Repository
public interface ImpUserRepository {
	public void deleteAll();

	public void deleteById(String userId);

	public boolean existsById(String userId);

	public EmployeeInfo findById(String userId);

	public void delete(EmployeeInfo userId);
	

}
