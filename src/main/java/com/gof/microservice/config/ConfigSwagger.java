package com.gof.microservice.config;



import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ConfigSwagger {
	
	@Bean
	public Docket configSwagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.any())
	            .paths(PathSelectors.regex("/api/.*"))
	            .build()
	            .apiInfo(apiInfo());
	    }

	    
	    private ApiInfo apiInfo() {
	        ApiInfo apiInfo = new ApiInfoBuilder().title(" My Project for Microservice ").description("this is the rest service for microservice")
	        		.contact(new Contact("Mohd Aashif", "https://gitlab.com/asdf7705889", "asdf7705889@gmail.com")).build();
	       
	        return apiInfo;
	    }
	}