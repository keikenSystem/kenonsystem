package com.keiken.kenonuserinterface.operationDTO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.repository.ImpUserRepository;

public class GetUserDataDto implements ImpUserRepository{
//Database connection 
	


	@Override
	public void delete(EmployeeInfo arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends EmployeeInfo> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<EmployeeInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<EmployeeInfo> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeInfo findById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeInfo> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeInfo> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
