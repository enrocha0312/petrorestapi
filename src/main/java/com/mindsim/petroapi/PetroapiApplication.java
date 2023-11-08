package com.mindsim.petroapi;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Petro API Project",
				version = "1.0",
				description = "Project to connect to Petro Server through via FTPS and generate tables by using the" +
						" files which the API downloads from this server",
				termsOfService = "Eduardo's work",
				contact = @Contact(
						name = "Eduardo N. da Rocha",
						email = "enrocha0312@gmail.com"
				),
				license = @License(
						name = "licence",
						url = "enrocha"
				)
		)
)
@SpringBootApplication
public class PetroapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetroapiApplication.class, args);
	}
}
