package com.tpark.tournament.controller;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Tournament;
import com.tpark.tournament.resource.TournamentResource;

@RestController
@EnableAutoConfiguration
@RequestMapping("/tournament")
public class TournamentController {
    private static Logger LOGGER = Logger.getLogger(TournamentController.class);
    @Autowired
    private TournamentRepository tournamentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{tournamentId}")
    public TournamentResource readTournament(@PathVariable Long tournamentId) {
        return new TournamentResource(this.tournamentRepository.findOne(tournamentId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        Tournament result = this.tournamentRepository.save(tournament);
        Link forOneTournament = new TournamentResource(result).getLink("self");
        return ResponseEntity.created(URI.create(forOneTournament.getHref())).build();
    }

}
