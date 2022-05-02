package com.example.GestorProcesionesPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "models")
public class GestorProcesionesPiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorProcesionesPiApplication.class, args);
	}

}
