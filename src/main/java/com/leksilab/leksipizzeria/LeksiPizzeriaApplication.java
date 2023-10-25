package com.leksilab.leksipizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LeksiPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeksiPizzeriaApplication.class, args);
	}

}
