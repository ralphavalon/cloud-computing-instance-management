package com.computing.cloud;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.computing.cloud.controller.global.AuthenticationInterceptor;

@SpringBootApplication
@EnableJSONDoc
public class CloudComputingApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(CloudComputingApplication.class, args);
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(authenticationInterceptor());
    }

    @Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}
	
}
