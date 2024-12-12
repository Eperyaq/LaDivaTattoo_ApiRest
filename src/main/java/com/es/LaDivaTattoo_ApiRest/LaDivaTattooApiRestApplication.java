package com.es.LaDivaTattoo_ApiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(RsaKeyProperties.class)
public class LaDivaTattooApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaDivaTattooApiRestApplication.class, args);
	}


}
