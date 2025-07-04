package com.ty.NotificationModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class NotificationModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationModuleApplication.class, args);
	}

}
