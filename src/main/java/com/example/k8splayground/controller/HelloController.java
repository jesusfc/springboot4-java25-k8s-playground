package com.example.k8splayground.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot 4!");
        response.put("timestamp", LocalDateTime.now());
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("springBootVersion", org.springframework.boot.SpringBootVersion.getVersion());
        return response;
    }

}
