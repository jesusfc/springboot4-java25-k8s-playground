package com.jesusfc.k8splayground.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.yaml.path:swagger.yaml}")
    private String yamlPath;

    @Bean
    public OpenAPI customOpenAPI() {
        File yamlFile = new File(yamlPath);

        if (yamlFile.exists()) {
            return new OpenAPIV3Parser().read(yamlPath);
        } else {
            throw new RuntimeException("Archivo YAML no encontrado: " + yamlPath);
        }
    }
}