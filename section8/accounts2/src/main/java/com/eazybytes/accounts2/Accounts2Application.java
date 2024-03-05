package com.eazybytes.accounts2;

import com.eazybytes.accounts2.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice Rest API Documentation",
				description = "EazyBank Accounts microservice REST Documentation",
				version = "v2",
				contact = @Contact(
						name = "Madan Redy",
						email = "madan@gmail.com",
						url = "https://www.oxu.az"
				),
				license = @License(
						name = "Apache2.0",
						url = "apache.org"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Eazybank Accounts microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
public class Accounts2Application {

	public static void main(String[] args) {
		SpringApplication.run(Accounts2Application.class, args);
	}

}
