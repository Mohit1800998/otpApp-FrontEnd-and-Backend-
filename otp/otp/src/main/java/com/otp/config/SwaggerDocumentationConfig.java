package com.otp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {

    @Value("${enable.swagger.plugin:true}")
    private boolean enableSwaggerPlugin;
  
    ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title("Swagger Email Services")
            .description("Email Inventory Service")
            .license("RAPIPAY")
            .licenseUrl("https://opensource.org/licenses/MIT")
            .version("1.0.0")
            .build();
    }

    @Bean
    public Docket customImplementation() {

      return new Docket(DocumentationType.SWAGGER_2)
          .useDefaultResponseMessages(false)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.otp.controller"))
          .paths(PathSelectors.any())
          .build()
          .enable(enableSwaggerPlugin)
          .apiInfo(apiInfo());
    }
}