package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cg.controller", "com.cg.service"})
public class FrontendFilmRentalStoreSprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendFilmRentalStoreSprintApplication.class, args);
	}
	
	@Configuration
	class AppConfig
	{
		@Bean
		public RestTemplate restTemplate()
		{
			return new RestTemplate();
		}
	}

}
