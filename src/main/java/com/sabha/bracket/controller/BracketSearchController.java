package com.sabha.bracket.controller;

import com.sabha.bracket.dataaccess.BracketRepository;
import com.sabha.bracket.entity.Bracket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * REST controller for Search related APIs.
 *
 * @author Swaroop Gaddameedhi
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/brackets/search")
public class BracketSearchController {
    private static Logger LOGGER = Logger.getLogger(BracketSearchController.class);

    @Autowired
    private BracketRepository bracketRepository;

    /**
     * Searches for brackets that match a provided search term.
     *
     * @param term The search term to be used for searching brackets.
     * @return Collection of potentially matching brackets.
     */
    @RequestMapping(method = RequestMethod.GET, params = {"term"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Collection<Bracket> search(@RequestParam(value = "term") String term) {
        LOGGER.info(String.format("Searching for searchTerm[%s]....", term));
        List<Bracket> result = this.bracketRepository.findAll();
        return result;
    }

    /**
     * Searches for brackets by bracket ID.
     *
     * @param id The bracket id.
     * @return {@link com.sabha.bracket.entity.Bracket}.
     */
    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public Collection<?> searchByBracketId(@RequestParam(value = "id") int id) {
        LOGGER.info(String.format("Searching for bracketId[%d]....", id));
        return this.bracketRepository.findAll();
    }
}

