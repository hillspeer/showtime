package org.test.showtime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Info info = new Info().title("ShowTime booking API")
                .version("1.0")
                .description("API Documentation");
        return new OpenAPI().info(info);
    }
}