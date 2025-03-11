package com.chrono.infra.security.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Configuração do OpenAPI para a documentação da API do Chrono.
 * 
 * Esta classe configura a documentação da API utilizando OpenAPI 3.0.
 * 
 * @return uma instância personalizada de {@link OpenAPI} com as informações da API e configurações de segurança.
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Informações do titulo no Swagger     
                .info(new Info()
                        .title("Chrono API Documentation")
                        .version("1.0")
                        .description("Documentação da API do Chrono para o projeto final do Trainee da @Wise\n\n" +
                                     "**AVISO: Para acessar os endpoints protegidos desta API, é necessário gerar um token JWT " +
                                     "através do endpoint de autenticação e incluí-lo no campo de autenticação 'Bearer' acima.**"))

                // Configuração de segurança para o Swagger - Exige um token para acessar a API
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .name("Bearer Authentication")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("É necessário gerar um token JWT através do endpoint de autenticação e incluí-lo aqui. Formato: Bearer {token}")
                        )
                );
    }
}
