package com.virtusa.banking.configurations;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
@Configuration
@EnableSwagger2WebMvc
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class CustomerSwaggerConfiguration {
    @Bean
    public Docket apiDocket() {
         return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.virtusa.banking"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "CUSTOMER API",
                "App to demonstrate API Documentation",
                "0.0.1-SNAPSHOT",
                "Terms of Service",
                new Contact("Ashish",
                        "https://blog.ashish.in",
                        "shambharkar.ashish@gmail.com"),
                "OSS License",
                "https://blog.ashish.in",
                Collections.emptyList());
    	}
    }