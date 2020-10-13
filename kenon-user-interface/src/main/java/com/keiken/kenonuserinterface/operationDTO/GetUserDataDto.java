package com.keiken.kenonuserinterface.operationDTO;



import org.springframework.context.annotation.ComponentScan;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.repository.ImpUserLoginOperation;
import com.keiken.kenonuserinterface.repository.ImpUserRepository;


@ComponentScan
public class GetUserDataDto implements ImpUserRepository,ImpUserLoginOperation{
  
	public GetUserDataDto() {
	// TODO Auto-generated constructor stub
	  
}

	@Override
	public boolean validatedUser(String userId, String password) {
		
		// TODO Auto-generated method stub
		System.out.println("Password checking");
		System.out.println("value "+userId);
		if(userId.equals("dummy")&&password.equals("pass"))
			return true;
		return false;
	}

	@Override
	public EmployeeInfo findById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(EmployeeInfo userId) {
		// TODO Auto-generated method stub
		
	}
	



	

}
