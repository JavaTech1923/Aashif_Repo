package com.gof.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableHystrix
public class CoupanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoupanServiceApplication.class, args);
		System.out.println("Coupan Provider Executed");
	}

}
