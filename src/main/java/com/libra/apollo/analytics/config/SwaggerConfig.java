package com.libra.apollo.analytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.libra.apollo.analytics.api"))
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "API Libra Investment Services analytics-service",
                "Apollo Analytics Service API to retrieve Investment Styles configurations to screen",
                "https://helloreverb.com/terms/",
                "libra_it@libra-is.com",
                "Libra Investment Services ",
                "Internal company use only", null
        );
        return apiInfo;
    }


}
