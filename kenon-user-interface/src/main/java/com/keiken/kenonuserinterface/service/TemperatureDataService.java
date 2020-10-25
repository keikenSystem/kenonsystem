package com.keiken.kenonuserinterface.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keiken.kenonuserinterface.model.EmployeeInfo;
import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;
import com.keiken.kenonuserinterface.repository.RepoTemperatureAndSymtomsOperation;

@Service
public class TemperatureDataService {

	// find Date in string  to set into the user information form 
	@Autowired
	RepoTemperatureAndSymtomsOperation tempRepo;
	
	public String getLastUsedDateText(String userId) {
		TemperatureAndSymtomsMesurement tempData =  tempRepo.findById(userId).get();
		Timestamp lastTimeStamp = tempData.getLastUsedTime();
	
		int year = lastTimeStamp.getYear();
		int month = lastTimeStamp.getMonth();
		int date = lastTimeStamp.getDate();
		int hour = lastTimeStamp.getHours();
		int min = lastTimeStamp.getMinutes();
		int sec = lastTimeStamp.getSeconds();
		System.out.println(year);
		return String.valueOf(1900+year)+"年"+String.valueOf(month+1)+"月"+String.valueOf(date)+"日"+String.valueOf(hour)+"時"+String.valueOf(min)+"分"+String.valueOf(sec)+"秒　使新";
		
	}
}
