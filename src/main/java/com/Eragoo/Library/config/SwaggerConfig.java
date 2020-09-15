package com.Eragoo.Library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfig {
    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .securitySchemes(List.of(new ApiKey("Authorization token", HttpHeaders.AUTHORIZATION, "header")))
                .securityContexts(List.of(securityContext()))
                .select()
                .apis(basePackage("com.Eragoo.Library"))
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(globalReference()))
                .build();
    }

    private SecurityReference globalReference() {
        final AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        return new SecurityReference("Authorization token", new AuthorizationScope[]{scope});
    }
}