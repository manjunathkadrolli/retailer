package com.retailer.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage("com.retailer.controller")).paths(regex("/.*")).build()
		.useDefaultResponseMessages(false).apiInfo(metaData());
    }

    private ApiInfo metaData() {
	return new ApiInfoBuilder().title("Demo REST API").description("\"Demo REST API's\"").version("1.0.0")
		.license("Demo License Version 1.0").licenseUrl("https://www.demo.com/licenses/LICENSE-1.0")
		.contact(new Contact("DEMO", "http://www.demo.com/demo-about/", "demo@gmail.com")).build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

	registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}