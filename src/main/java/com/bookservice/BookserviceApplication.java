package com.bookservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication
@EnableDiscoveryClient

public class BookserviceApplication {
	 
	public static void main(String[] args) {
		 Logger logger
	     = LoggerFactory.getLogger(BookserviceApplication.class);
		SpringApplication.run(BookserviceApplication.class, args);
		logger.info("Book Service is Up and Running ");
		
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	  
	
	
	
}
