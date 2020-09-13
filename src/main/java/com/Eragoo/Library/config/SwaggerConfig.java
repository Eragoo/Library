package com.Eragoo.Library.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Set;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.schema.AlternateTypeRules.newRule;
import static springfox.documentation.spi.DocumentationType.OAS_30;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@AllArgsConstructor
public class SwaggerConfig {
    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(basePackage("com.Eragoo.Library"))
                .build();
    }
}