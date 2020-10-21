package com.keiken.kenonuserinterface.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.LoginTable;

@Repository
public interface RepoLoginTable extends CrudRepository<LoginTable, String>{
	

}
