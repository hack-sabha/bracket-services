package com.tpark.tournament.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tpark.tournament.controller.TournamentSearchController;

/**
 * Entry point application to tournament Services
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.tpark.tournament")
@EnableJpaRepositories("com.tpark.tournament.dataaccess")
@EntityScan("com.tpark.tournament.entity")
public class TournamentServicesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TournamentServicesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TournamentServicesApplication.class, TournamentSearchController.class);
    }
}
