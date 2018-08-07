package com.takuba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.takuba")
@EnableDiscoveryClient
public class ComicsSpringFeingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicsSpringFeingApplication.class, args);
	}
}
