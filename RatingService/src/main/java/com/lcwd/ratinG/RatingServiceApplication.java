package com.lcwd.ratinG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@SpringBootApplication
@EnableDiscoveryClient
public class RatingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RatingServiceApplication.class, args);
	}

}
