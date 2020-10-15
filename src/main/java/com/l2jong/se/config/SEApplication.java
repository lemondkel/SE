package com.l2jong.se.config;

import com.l2jong.se.repo.BrdRepository;
import com.l2jong.se.repo.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.l2jong.se")
@EntityScan("com.l2jong.se.entity")
@EnableJpaRepositories("com.l2jong.se.repo")
public class SEApplication {

	@Autowired
	private UsrRepository usrRepository;

	@Autowired
	private BrdRepository brdRepository;

	public static void main(String[] args) {
		SpringApplication.run(SEApplication.class, args);
	}

}
