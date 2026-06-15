package com.ous.aethererp.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // This add an authorize button in swagger where users can paste JWT tokens.
    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info().title("Aether ERP Auth").version("v1.0")
                        .description("User Authentication with JWT."))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .schemaRequirement("Bearer Authentication",
                        new SecurityScheme().name("Authorization").type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }
}
