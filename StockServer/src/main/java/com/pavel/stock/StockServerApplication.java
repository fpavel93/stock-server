package com.pavel.stock;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StockServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServerApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.pavel.stock")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Stock items API",
				"Stock items API for OpenLegacy exercise developed by Pavel Furen",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Pavel Furen", "https://www.linkedin.com/in/pavelfuren", "fpavel93@gmail.com"),
				"API License",
				"https://www.linkedin.com/in/pavelfuren",
				Collections.emptyList());
	}
}
