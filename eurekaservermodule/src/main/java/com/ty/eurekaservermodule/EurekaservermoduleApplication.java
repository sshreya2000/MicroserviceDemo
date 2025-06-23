package com.ty.eurekaservermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaservermoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaservermoduleApplication.class, args);
	}

}
