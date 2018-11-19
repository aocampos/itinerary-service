package aocampos.itinerary.itryrouteservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("aocampos.itinerary.itryrouteservice"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Itinerary route API",
                "Collection of APIs to handle Cities and Travels",
                "v1.0.0",
                "Terms of service",
                new Contact("Alex Orives Campos", "https://github.com/aocampos", "aocampos86@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
