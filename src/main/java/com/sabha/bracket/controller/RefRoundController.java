package com.sabha.bracket.controller;


import com.sabha.bracket.dataaccess.RefRoundRepository;
import com.sabha.bracket.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
