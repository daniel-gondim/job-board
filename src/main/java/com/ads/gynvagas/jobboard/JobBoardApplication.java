package com.ads.gynvagas.jobboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JobBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);
	}

}
