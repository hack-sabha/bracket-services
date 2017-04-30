package com.tpark.tournament.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.tpark.tournament.controller.TournamentBracketController;
import com.tpark.tournament.controller.TournamentController;
import com.tpark.tournament.controller.TournamentParticipantGroupController;
import com.tpark.tournament.controller.TournamentTeamController;
import com.tpark.tournament.entity.Tournament;

public class TournamentResource extends ResourceSupport {
    private Tournament tournament;

    public TournamentResource(Tournament tournament) {
        this.tournament = tournament;
        this.add(linkTo(methodOn(TournamentController.class).readTournament(tournament.getId())).withSelfRel());
        this.add(linkTo(methodOn(TournamentParticipantGroupController.class).add(tournament.getId(), null)).withRel("addParticipants"));
        this.add(linkTo(methodOn(TournamentTeamController.class).add(tournament.getId(), null)).withRel("addTeams"));
        this.add(linkTo(methodOn(TournamentBracketController.class).read(tournament.getId())).withRel("getBracket"));

    }

    public Tournament getTournament() {
        return tournament;
    }
}
