package com.CineHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CineHubApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CineHubApplication.class);

		// Obtener puerto desde variable de entorno
		String port = System.getenv("PORT");
		if (port == null) port = "8080"; // Si no existe variable, uso 8080 para entorno local

		Map<String, Object> props = new HashMap<>();
		// Introduzco la propiedad que Spring Boot usa para saber en qué puerto arrancar el servidor Tomcat.
		props.put("server.port", port);
		app.setDefaultProperties(props);
		app.run(args);
	}
}