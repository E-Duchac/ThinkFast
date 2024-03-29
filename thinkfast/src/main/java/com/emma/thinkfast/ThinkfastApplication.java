package com.emma.thinkfast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//Note to self: Remove the exclude part after security is configured.
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ThinkfastApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThinkfastApplication.class, args);
	}

}
