package com.tpark.tournament.controller;

import java.util.List;

import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.RefRoundRepository;
import com.tpark.tournament.entity.Round;

@RestController
@EnableAutoConfiguration
public class RefRoundController {
    private static Logger LOGGER = Logger.getLogger(RefRoundController.class);

    @Autowired
    private RefRoundRepository roundRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/service/rounds", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Sports & Rounds APIs"}, value = "Gets all available Rounds", nickname = "GetRounds")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Round.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public List<Round> handleRequest() {
        LOGGER.info("Retrieving all Rounds...");
        return this.roundRepository.findAll();
    }
}
