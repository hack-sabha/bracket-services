package com.tpark.tournament.controller;

import java.util.Collection;
import java.util.List;

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
@RequestMapping("/tournaments/search")
public class TournamentSearchController {
    private static Logger LOGGER = Logger.getLogger(TournamentSearchController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    /**
     * Searches for tournaments that match a provided search term.
     *
     * @param term
     *            The search term to be used for searching tournaments.
     * @return Collection of potentially matching tournaments.
     */
    @RequestMapping(method = RequestMethod.GET, params = { "term" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Collection<Tournament> search(@RequestParam(value = "term") String term) {
        LOGGER.info(String.format("Searching for searchTerm[%s]....", term));
        return this.tournamentRepository.findByNameIgnoreCaseContaining(term);
    }

}
