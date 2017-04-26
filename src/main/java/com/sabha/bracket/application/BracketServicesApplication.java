package com.sabha.bracket.application;

import com.sabha.bracket.controller.BracketSearchController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Entry point application to Bracket Services
 *
 * @author Swaroop Gaddameedhi.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.sabha.bracket")
@EnableJpaRepositories("com.sabha.bracket.dataaccess")
@EntityScan("com.sabha.bracket.entity")
public class BracketServicesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BracketServicesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BracketServicesApplication.class, BracketSearchController.class);
    }
}
