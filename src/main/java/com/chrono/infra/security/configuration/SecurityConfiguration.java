package com.chrono.infra.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    // Disable protetion against attacks in the username, start stateless session and turn on permissions for url's 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize 
                    // Public routes for all
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                    // Protected routes of user only by admin
                    .requestMatchers(HttpMethod.POST, "/v1/user").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/v1/user/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/user/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/user/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/user").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/user/name").hasRole("ADMIN")

                    // Protected routes of projects only by admin
                    .requestMatchers(HttpMethod.POST, "/v1/project").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/v1/project/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/project/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/project/{id}").hasRole("ADMIN")

                    // Protected routes of activity only by admin
                    .requestMatchers(HttpMethod.POST, "/v1/activity").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/v1/activity/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/activity/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/activity/{id}").hasRole("ADMIN")

                    // Protected routes of hours only by admin
                    .requestMatchers(HttpMethod.POST, "/v1/hours").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/v1/hours/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/hours/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/hours/{id}").hasRole("ADMIN")

                    .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Submit a request for prover and view the auth flow
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Permissions for Token read password with BCryptEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}