package com.liaacosta.armariovirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class ArmariovirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArmariovirtualApplication.class, args);
	}

}