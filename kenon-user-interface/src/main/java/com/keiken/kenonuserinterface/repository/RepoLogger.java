package com.keiken.kenonuserinterface.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.keiken.kenonuserinterface.model.TemperatureAndSymtomsMesurement;

public interface RepoLogger extends CrudRepository<TemperatureAndSymtomsMesurement, Integer>{
	
	@Query("From TemperatureAndSymtomsMesurement where Date(lastUsedTime)=?1")
	public List<TemperatureAndSymtomsMesurement> getListByDate(Date selectedDate);

	@Query(value="select * from m_user_temparature,m_user where m_user.社員番号=fk_user_id and Date(m_user_temparature.更新日時)<= ?1 and Date(m_user_temparature.更新日時)>=?2 and m_user.部門=?3",nativeQuery = true)
	public List<TemperatureAndSymtomsMesurement> getHealthInfoByDate(String selectedDate,Date fiveDaysBefore,String department);
	@Query(value="select * from m_user_temparature where Date(m_user_temparature.更新日時)<= ?1 and Date(m_user_temparature.更新日時)>=?2",nativeQuery = true)
	public List<TemperatureAndSymtomsMesurement> getHealthInfoForAll(String selectedDate,Date fiveDaysBefore);

}
