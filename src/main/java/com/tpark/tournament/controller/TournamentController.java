package com.tpark.tournament.controller;

import java.net.URI;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpark.tournament.dataaccess.RefTournamentTypeRepository;
import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.RefTournamentType;
import com.tpark.tournament.entity.Tournament;
import com.tpark.tournament.resource.TournamentResource;

@RestController
@EnableAutoConfiguration
public class TournamentController {
    private static Logger LOGGER = Logger.getLogger(TournamentController.class);
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RefTournamentTypeRepository tournamentTypeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/service/tournament/{tournamentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Tournament APIs"}, value = "Get Tournament", nickname = "GetTournament")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = TournamentResource.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public TournamentResource readTournament(@PathVariable Long tournamentId) {
        LOGGER.info(String.format("Retrieving the tournament with id [%d]...", tournamentId));
        return new TournamentResource(this.tournamentRepository.findOne(tournamentId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/service/tournament/types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Tournament APIs"}, value = "Get All Tournament Types", nickname = "GetTournamentTypes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = RefTournamentType.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public List<RefTournamentType> readTypes() {
        LOGGER.info("Retrieving all tournament types...");
        return this.tournamentTypeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/service/tournament", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Tournament APIs"}, value = "Add Tournament", nickname = "AddTournament")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Tournament.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        LOGGER.info("Creating a new tournament. " + tournament.toString());
        Tournament result = this.tournamentRepository.save(tournament);
        Link forOneTournament = new TournamentResource(result).getLink("self");
        return ResponseEntity.created(URI.create(forOneTournament.getHref())).build();
    }

}
