package com.tpark.tournament.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.TeamRepository;
import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Team;

@RestController
@EnableAutoConfiguration
@RequestMapping("/tournament/{tournamentId}/teams")
public class TournamentTeamController {

    private static Logger LOGGER = Logger.getLogger(TournamentTeamController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Team> add(@PathVariable long tournamentId, @RequestBody List<Team> teams) {

        return null;
    }

}
