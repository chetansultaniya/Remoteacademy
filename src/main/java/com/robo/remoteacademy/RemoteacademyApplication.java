package com.robo.remoteacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RemoteacademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteacademyApplication.class, args);
	}

}

