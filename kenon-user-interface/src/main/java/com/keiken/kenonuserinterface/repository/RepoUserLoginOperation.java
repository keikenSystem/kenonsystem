package com.keiken.kenonuserinterface.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.RegistrationInfo;

@Repository
public interface RepoUserLoginOperation extends CrudRepository<RegistrationInfo, String>{
	//Invite with token;


}
