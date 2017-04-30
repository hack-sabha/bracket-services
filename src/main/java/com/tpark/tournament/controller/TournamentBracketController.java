package com.tpark.tournament.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.resource.TournamentBracketResource;

@RestController
@EnableAutoConfiguration
@RequestMapping("/tournament/{tournamentId}/bracket")
public class TournamentBracketController {

    private static Logger LOGGER = Logger.getLogger(TournamentBracketController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    // BracketRepository

    @RequestMapping(method = RequestMethod.GET)
    public TournamentBracketResource read(@PathVariable long tournamentId) {

        return null;
    }

}
