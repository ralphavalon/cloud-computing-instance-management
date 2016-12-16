package com.computing.cloud;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class CloudComputingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudComputingApplication.class, args);
	}
	
}
