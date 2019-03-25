package com.robo.remoteacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.robo.remoteacademy.controller.DemoController;

@SpringBootApplication
@EnableJpaAuditing
public class RemoteacademyApplication {

	private static final Logger LOGGER = LogManager.getLogger(DemoController.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RemoteacademyApplication.class, args);
		LOGGER.info("**********Application Started************");
	}

}

