package com.keiken.kenonuserinterface.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.DatewiseUserHandler;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.repository.RepoUserUpdatedTime;

@Service
public class TemperatureDataService {

	// find Date in string to set into the user information form
	@Autowired
	RepoUserUpdatedTime timeRepo;
	
	@Autowired
	RepoUserUpdatedTime repoUserDataService;
	
	
	

	public String getLastUsedDateText(String userId) {
		
		try {	
			DatewiseUserHandler timeData = timeRepo.findById(userId).get();
		
			Timestamp lastTimeStamp = timeData.getLastUsedTime();
			//Check it this user exists in  tempareture table
			 DatewiseUserHandler datewiseUserHandler = timeRepo.findById(userId).get();
			  if(datewiseUserHandler.getTempAndSymtoms().size()>0) {
			 
			
		
			

			int year = lastTimeStamp.getYear();
			int month = lastTimeStamp.getMonth();
			int date = lastTimeStamp.getDate();
			int hour = lastTimeStamp.getHours();
			int min = lastTimeStamp.getMinutes();
			int sec = lastTimeStamp.getSeconds();
			System.out.println(year);
			return String.valueOf(1900 + year) + "年" + String.valueOf(month + 1) + "月" + String.valueOf(date) + "日"
					+ String.valueOf(hour) + "時" + String.valueOf(min) + "分" + String.valueOf(sec) + "秒　使新";
		
			}
			  else {
				  return "never insert temperature yet";
			  }
			  
		}
		catch (Exception e) {
			return "never insert temperature yet";
		}
		
		
	}
	
	public double setRound(int place,double value) {
		 BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
		   bigDecimal = bigDecimal.setScale(place, RoundingMode.HALF_UP);
		   
		   return bigDecimal.doubleValue();
	}


	public void addData(TemperatureAndSymtomsMesurement tempData) {
		 String userId = tempData.getUserId();
		 Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		tempData.setTemperature(setRound(1,tempData.getTemperature()));
		
		DatewiseUserHandler dateHandler = timeRepo.findById(userId).get();
		dateHandler.getTempAndSymtoms().add(new TemperatureAndSymtomsMesurement(userId, tempData.getTemperature(), tempData.isGotSymtoms(), currentTime, dateHandler));
		dateHandler.setLastUsedTime(currentTime);
		timeRepo.save(dateHandler);
		
		dateHandler = timeRepo.findById(userId).get();
		for( int i=0;i<dateHandler.getTempAndSymtoms().size();i++) {
			System.out.println(dateHandler.getTempAndSymtoms().get(i));
		}
		
	}
	
}
