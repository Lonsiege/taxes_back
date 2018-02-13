package com.iliap.taxesManager;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TaxesManagerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TaxesManagerApplication.class, args);
		LocalDateTime startTime = LocalDateTime.now();
		System.out.println("Taxes application started at time "+startTime);

		
	}
}
