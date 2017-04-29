package com.tpark.tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpark.tournament.dataaccess.RefRoundRepository;
import com.tpark.tournament.entity.Round;

@RestController
@EnableAutoConfiguration
public class RefRoundController {
    @Autowired
    private RefRoundRepository roundRepository;

    @RequestMapping("/rounds")
    public List<Round> handleRequest() {
        return this.roundRepository.findAll();
    }
}
