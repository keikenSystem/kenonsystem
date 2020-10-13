package com.keiken.kenonuserinterface.repository;

import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.UserRegisteredInfo;

@Repository
public interface ImpUserLoginOperation {
	//Invite with token;
	public boolean validatedUser(String userId,String password);

}
