package ru.interpol.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "${spring.application.name}",
                version = "${spring.application.version}",
                description = "${spring.application.description}"
        ),
        servers = @Server(
                url = "${spring.application.swagger.url}",
                description = "${spring.application.swagger.server-description}"
        )
)public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
