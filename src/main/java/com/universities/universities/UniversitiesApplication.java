package com.universities.universities;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversitiesApplication.class, args);
		System.out.println("\n\n-------> Server Running... <-------------------\n\n");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
