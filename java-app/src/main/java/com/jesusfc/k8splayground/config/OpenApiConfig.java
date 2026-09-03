package com.jesusfc.k8splayground.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "k8s-playground API",
        version = "1.0.0",
        description = "API REST para pruebas y validación del entorno Kubernetes con Spring Boot 4"
    )
)
public class OpenApiConfig {
}
