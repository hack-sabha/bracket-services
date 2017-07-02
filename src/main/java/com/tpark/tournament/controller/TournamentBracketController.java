package com.tpark.tournament.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.resource.TournamentBracketResource;

@RestController
@EnableAutoConfiguration
public class TournamentBracketController {
    private static Logger LOGGER = Logger.getLogger(TournamentBracketController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/service/tournament/{tournamentId}/bracket", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Tournament APIs"}, value = "Get Tournament Bracket", nickname = "GetTournamentBracket")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = TournamentBracketResource.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public TournamentBracketResource read(@PathVariable long tournamentId) {
        LOGGER.info(String.format("Retrieving the tournament bracket for tournamentId[%d]...", tournamentId));
        return null;
    }

}
