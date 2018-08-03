package com.takuba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ComicsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicsSpringBootApplication.class, args);
	}
}
