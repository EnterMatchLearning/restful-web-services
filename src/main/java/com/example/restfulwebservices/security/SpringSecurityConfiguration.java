package com.example.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//      1) All requests must be authenticated
        http.authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated()
        );

//      2) If a request is not authenticated, basic authentication is used
        http.httpBasic(withDefaults());

//      3) Disable CSRF
        http.csrf().disable();

        return http.build();
    }
}
