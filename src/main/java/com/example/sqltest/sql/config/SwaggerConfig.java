package com.example.sqltest.sql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger configuration for Spring Boot JPA CRUD
 *
 * @author Jfredricks
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Sets the base package and base URL
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.sqltest.sql.controller"))
                .paths(regex("/db.*"))
                .build();
    }

    /**
     * Displays the ApiInfo
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot JPA CRUD Operations")
                .description("An Implementation of JPA CRUD Operations in Spring Boot")
                .version("1.0")
                .build();
    }
}