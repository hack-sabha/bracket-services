package com.tpark.tournament.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.RefSportRepository;
import com.tpark.tournament.entity.Sport;

@RestController
@EnableAutoConfiguration
@RequestMapping("/sports")
public class RefSportController {
    private static Logger LOGGER = Logger.getLogger(RefSportController.class);
    @Autowired
    private RefSportRepository sportRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Sport> readSports() {
        return sportRepository.findAll();
    }
}
