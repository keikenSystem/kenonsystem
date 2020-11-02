package com.keiken.kenonuserinterface.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiken.kenonuserinterface.model.EmployeeInfo;

@Repository
public interface RepoUser extends CrudRepository<EmployeeInfo, String> {

	
	@Query("From EmployeeInfo where email=?1")
	List<EmployeeInfo> findUserByEmail(String email);
	
	@Query(value = "SELECT DISTINCT(部門) FROM `m_user`",nativeQuery = true)
	List<String> findDistictDepartmentName();

}
