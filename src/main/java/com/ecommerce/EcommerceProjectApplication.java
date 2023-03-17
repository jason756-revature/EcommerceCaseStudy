package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProjectApplication.class, args);
	}

}

//Notes:
//A connection to a database is required
//We specify the database connection details in the 'application.properties' or by creating a 'application.yml' file

//When you run Spring Boot Application, Spring will only scan the classes below your main class package.
//So, make sure that your main class is in a root package above other classes.