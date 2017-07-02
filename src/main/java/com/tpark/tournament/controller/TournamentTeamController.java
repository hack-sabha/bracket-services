package com.tpark.tournament.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpark.tournament.dataaccess.TeamRepository;
import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Team;

@RestController
@EnableAutoConfiguration
public class TournamentTeamController {

    private static Logger LOGGER = Logger.getLogger(TournamentTeamController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/service/tournament/{tournamentId}/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = {"Tournament APIs"}, value = "Add Teams to Tournament", nickname = "AddTournamentTeams")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Team.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Team> add(@PathVariable long tournamentId, @RequestBody List<Team> teams) {
        LOGGER.info(String.format("Adding teams to Tournament[%d]. Teams[%s]", tournamentId, teams.toString()));
        return null;
    }
}
