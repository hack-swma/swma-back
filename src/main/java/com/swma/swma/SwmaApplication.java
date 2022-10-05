package com.swma.swma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SwmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwmaApplication.class, args);
	}

}
