package edu.miu.assignment.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiDocsConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("WAA Assignment API")
                        .description("Assignment for MIU WAA course by Aung Thu Moe")
                        .version("0.0.1"));
    }
}
