package com.jaxrs.sandbox.shopping;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShoppingServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(ShoppingServiceApplication.class, args);
	}


}
