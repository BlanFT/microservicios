package com.microservice.bike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceBikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBikeApplication.class, args);
	}

}
