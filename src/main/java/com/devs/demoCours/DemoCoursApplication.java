package com.devs.demoCours;

import com.devs.demoCours.Configuration.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCoursApplication {

	public static void main(String[] args) {
		DotenvConfig.loadEnv();
		SpringApplication.run(DemoCoursApplication.class, args);
	}

}
