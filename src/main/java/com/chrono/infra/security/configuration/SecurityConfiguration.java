package com.chrono.infra.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chrono.infra.security.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    /**
     * Configura a cadeia de filtros de segurança.
     * 
     * @param httpSecurity o objeto HttpSecurity
     * @return a cadeia de filtros de segurança configurada
     * @throws Exception se ocorrer um erro durante a configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    // Rotas públicas para todos
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                    // Configuração do Swagger
                    .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-resources"
                    ).permitAll()

                    // Rotas protegidas acessíveis apenas por admin
                    .requestMatchers(HttpMethod.POST, "/v1/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/v1/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/**").hasRole("ADMIN")
                    
                    .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(authenticationEntryPoint())  // Erros de autenticação (ex: 401)
                    .accessDeniedHandler(accessDeniedHandler())  // Erros de autorização (ex: 403)
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); 
    }

    /**
     * Manipulador de acesso negado.
     * 
     * @return o manipulador de acesso negado
     */
    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Access Denied", accessDeniedException.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        };
    }

    /**
     * Ponto de entrada de autenticação.
     * 
     * @return o ponto de entrada de autenticação
     */
    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", authException.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        };
    }

    /**
     * Gerenciador de autenticação.
     * 
     * @param authenticationConfiguration a configuração de autenticação
     * @return o gerenciador de autenticação
     * @throws Exception se ocorrer um erro durante a configuração
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Codificador de senha usando BCrypt.
     * 
     * @return o codificador de senha
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
