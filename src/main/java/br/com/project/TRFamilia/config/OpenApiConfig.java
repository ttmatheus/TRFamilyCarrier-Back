package br.com.project.TRFamilia.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	private static final String SECURITY_SCHEME_NAME = "BearerAuth";

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
			.info(new Info().title("TRFamilia API").version("1.0.0"))
			.components(
				new Components()
				.addSecuritySchemes(SECURITY_SCHEME_NAME,
					new SecurityScheme()
						.name("Authorization")
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")
					)	
			);
	}

	@Bean
	public GroupedOpenApi securedApi() {
		return GroupedOpenApi.builder()
			.group("secured")
			.pathsToMatch("/**")
			.pathsToExclude("/auth/login", "/swagger-ui/**", "/v3/api-docs/**")
			.addOperationCustomizer((operation, handlerMethod) -> {
				operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
				return operation;
			})
			.build();
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
			.group("public")
			.pathsToMatch("/auth/login")
			.build();
	}
}
