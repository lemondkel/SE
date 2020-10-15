package com.l2jong.se.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.l2jong.se")
public class SEApplication {

	public static void main(String[] args) {
		SpringApplication.run(SEApplication.class, args);
	}

}
