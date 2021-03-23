package com.walrus.serviceundererred;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceundererredApplication {

	private static final Logger logger = LoggerFactory.getLogger(ServiceundererredApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Server");
		SpringApplication.run(ServiceundererredApplication.class, args);
	}

}
