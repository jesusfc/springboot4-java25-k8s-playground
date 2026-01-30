package com.example.playground;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Allow public access to API endpoints
                .requestMatchers("/api/**").permitAll()
                // Allow public access to health endpoint for Kubernetes probes
                .requestMatchers(EndpointRequest.to("health")).permitAll()
                // Require authentication for all other actuator endpoints
                .requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                // Require authentication for everything else
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {})
            .csrf(csrf -> csrf.disable()); // Disabled for simplicity in playground

        return http.build();
    }

}
