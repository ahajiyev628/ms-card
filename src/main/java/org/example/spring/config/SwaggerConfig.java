package org.example.spring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Card API", version = "1.0", description = "API Documentation"))
public class SwaggerConfig {
//    http://localhost:8989/swagger-ui/index.html
}

