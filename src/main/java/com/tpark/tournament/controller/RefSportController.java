package com.tpark.tournament.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.RefSportRepository;
import com.tpark.tournament.entity.Sport;

@RestController
@EnableAutoConfiguration
public class RefSportController {
    private static Logger LOGGER = Logger.getLogger(RefSportController.class);

    @Autowired
    private RefSportRepository sportRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/service/sports", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Sports & Rounds APIs"}, value = "Gets all available Sports", nickname = "GetSports")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Sport.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public List<Sport> readSports() {
        LOGGER.info("Retrieving all Sports...");
        return sportRepository.findAll();
    }
}
