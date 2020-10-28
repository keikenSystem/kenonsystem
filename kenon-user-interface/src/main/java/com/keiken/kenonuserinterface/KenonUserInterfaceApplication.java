package com.keiken.kenonuserinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


///Main Application starts from this class


@EnableScheduling
@SpringBootApplication
public class KenonUserInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KenonUserInterfaceApplication.class, args);
	}

}



