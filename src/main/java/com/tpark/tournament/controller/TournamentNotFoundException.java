package com.tpark.tournament.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TournamentNotFoundException extends RuntimeException {

    public TournamentNotFoundException(long tournamentId) {
        super("could not find tournament '" + tournamentId + "'.");
    }
}
