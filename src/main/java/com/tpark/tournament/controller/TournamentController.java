package com.tpark.tournament.controller;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Tournament;

@RestController
@EnableAutoConfiguration
@RequestMapping("/tournament")
public class TournamentController {

    private static Logger LOGGER = Logger.getLogger(TournamentController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/id/{tournamentId}")
    Tournament read(@PathVariable Long tournamentId) {
        return this.tournamentRepository.findOne(tournamentId);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Tournament> read(@RequestBody Tournament tournament) {
        Tournament result = this.tournamentRepository.save(tournament);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getTournamentId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
