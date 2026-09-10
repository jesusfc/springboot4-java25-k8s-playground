package com.jesusfc.k8splayground.controller;

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


        LocalDateTime entryTime = LocalDateTime.now();

        // create wait time to simulate a long-running process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LocalDateTime responseTime = LocalDateTime.now();
        // Difference in milliseconds
        long duration = java.time.Duration.between(entryTime, responseTime).toMillis();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot 4!");
        response.put("entryTime: ", entryTime.toString());
        response.put("responseTime: ", responseTime.toString());
        response.put("duration: ", duration + " ms");
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("springBootVersion", org.springframework.boot.SpringBootVersion.getVersion());
        response.put("podName", System.getenv().getOrDefault("HOSTNAME", "local"));
        System.out.println("Hello endpoint was called at " + LocalDateTime.now());
        return response;
    }

}
