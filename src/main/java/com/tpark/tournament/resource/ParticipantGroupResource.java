package com.tpark.tournament.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.tpark.tournament.controller.TournamentBracketController;
import com.tpark.tournament.controller.TournamentController;
import com.tpark.tournament.controller.TournamentParticipantGroupController;

public class ParticipantGroupResource extends ResourceSupport {
    private TournamentParticipantGroupController.ParticipantGroup group;

    public ParticipantGroupResource(TournamentParticipantGroupController.ParticipantGroup group, long tournamentId) {
        this.group = group;
        this.add(linkTo(methodOn(TournamentController.class).readTournament(tournamentId)).withRel("tournament"));
        this.add(linkTo(methodOn(TournamentParticipantGroupController.class).read(tournamentId)).withSelfRel());
        this.add(linkTo(methodOn(TournamentBracketController.class).read(tournamentId)).withRel("getBracket"));
    }

    public TournamentParticipantGroupController.ParticipantGroup getGroup() {
        return group;
    }
}
