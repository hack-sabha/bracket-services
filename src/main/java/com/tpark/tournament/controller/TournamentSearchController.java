package com.tpark.tournament.controller;


import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Tournament;

/**
 * REST controller for Search related APIs for tournaments
 */
@RestController
@EnableAutoConfiguration
public class TournamentSearchController {
    private static Logger LOGGER = Logger.getLogger(TournamentSearchController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    /**
     * Searches for tournaments that match a provided search term.
     *
     * @param term The search term to be used for searching tournaments.
     * @return Collection of potentially matching tournaments.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/service/tournaments/search", params = {"term"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Tournament APIs"}, value = "Search Tournament", nickname = "SearchTournament")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Tournament.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public Collection<Tournament> search(@RequestParam(value = "term") String term) {
        LOGGER.info(String.format("Searching for searchTerm[%s]....", term));
        return this.tournamentRepository.findByNameIgnoreCaseContaining(term);
    }

    /**
     * Retrieves all tournaments.
     *
     * @return List of all tournaments in DB.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/service/tournaments/all")
    @ApiOperation(tags = {"Tournament APIs"}, value = "Get All Tournaments", nickname = "GetTournaments")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Tournament.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public List<Tournament> getAllTournaments() {
        LOGGER.info("Retrieving all tournaments...");
        return this.tournamentRepository.findAll();
    }
}
