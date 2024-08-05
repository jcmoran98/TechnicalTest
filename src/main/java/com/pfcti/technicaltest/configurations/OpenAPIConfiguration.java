package com.pfcti.technicaltest.configurations;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

	@Bean
	OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:5000");
		server.setDescription("Development");

		Contact myContact = new Contact();
		myContact.setName("José Carlos Chávez");
		myContact.setEmail("carlos.ch.moran@gmail.com");

		Info information = new Info().title("Customer Management System API").version("0.0.1")
				.description("This API exposes endpoints to manage customers.").contact(myContact);
		return new OpenAPI().info(information).servers(List.of(server));
	}
}
