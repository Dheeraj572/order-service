package com.mindtree.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableAspectJAutoProxy
public class OrderServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		
	}

}
