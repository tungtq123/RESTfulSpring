package com.tqt.restfulspring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("")
    private String apiDocPath;
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Management API")
                        .version("1.0")
                        .contact(new Contact().url("http://localhost:8080/")
                                .email("fu@org.vn")
                                .url("http://localhost:8080/")
                                .name("FU"))
                        .description("API for managing product items and categories"))
                .addServersItem(new Server().url("http://localhost:8080" + apiDocPath));
    }
}