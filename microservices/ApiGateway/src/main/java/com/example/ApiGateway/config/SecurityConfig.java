package com.example.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchange -> exchange
                        .anyExchange().authenticated()
                )
                .oauth2Client(withDefaults -> {})
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults -> {}))
                .build();
    }
}

// always Security -> api -> default -> access policy should be there
// pibal74801@dextrago.com atko@123 
// redirect uri in the app -> http://localhost:4044/login/oauth2/code/okta