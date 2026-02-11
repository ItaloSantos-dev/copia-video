package com.italo.copiavideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CopiaVideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopiaVideoApplication.class, args);
	}

}
