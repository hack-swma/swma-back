package com.swma.swma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ConfigurationPropertiesScan
@EnableCaching
@SpringBootApplication
public class SwmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwmaApplication.class, args);
	}

}
