package com.tpark.tournament.application;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.tpark.tournament.controller.RefSportController;
import com.tpark.tournament.controller.TournamentController;
import com.tpark.tournament.controller.TournamentParticipantGroupController;
import com.tpark.tournament.controller.TournamentSearchController;

/**
 * Entry point application to tournament Services
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.tpark.tournament")
@EnableJpaRepositories("com.tpark.tournament.dataaccess")
@EntityScan("com.tpark.tournament.entity")
public class TournamentServicesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TournamentServicesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TournamentServicesApplication.class, TournamentSearchController.class, TournamentController.class, TournamentParticipantGroupController.class, RefSportController.class);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName("TournamentServices")
            .apiInfo(apiInfo())
            .select()
            .paths(regex("/service.*"))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("TourPark Services")
            .license("TourPark © Copyright License")
            .licenseUrl("https://github.com/tour-park/tournament-services/LICENSE")
            .version("0.1.0-SNAPSHOT")
            .build();
    }
}
