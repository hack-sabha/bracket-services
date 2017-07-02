package com.tpark.tournament.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tpark.tournament.dataaccess.ParticipantRepository;
import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Participant;
import com.tpark.tournament.entity.Tournament;
import com.tpark.tournament.resource.ParticipantGroupResource;

@RestController
@EnableAutoConfiguration
public class TournamentParticipantGroupController {
    private static Logger LOGGER = Logger.getLogger(TournamentParticipantGroupController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/service/tournament/{tournamentId}/participants")
    @ApiOperation(tags = {"Tournament APIs"}, value = "Add Participants to Tournament", nickname = "AddTournamentParticipants")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Tournament.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<Tournament> add(@PathVariable long tournamentId, @RequestBody ParticipantGroup participantGroup) {
        LOGGER.info(String.format("Adding participants to tournament[%s].", tournamentId));

        // TODO validate names, generate random seeds
        Tournament tournament = validateTournament(tournamentId);

        // TODO use java 8 features
        List<Participant> participantsToSave = new ArrayList<>();
        int seed = 0;
        for (Participant participant : participantGroup.getParticipants()) {
            participantsToSave.add(new Participant(participant.getParticipantName(), tournament, null, Long.valueOf(++seed)));
        }
        participantRepository.save(participantsToSave);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/service/tournament/{tournamentId}/participants")
    @ApiOperation(tags = {"Tournament APIs"}, value = "Get Tournament Participants", nickname = "GetTournamentParticipants")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = ParticipantGroupResource.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public ParticipantGroupResource read(@PathVariable long tournamentId) {
        LOGGER.info(String.format("Adding participants to tournament[%s].", tournamentId));
        validateTournament(tournamentId);
        ParticipantGroup participantGroup = new ParticipantGroup();
        participantGroup.setParticipants(participantRepository.findByTournamentId(tournamentId));
        return new ParticipantGroupResource(participantGroup, tournamentId);
    }

    public static class ParticipantGroup {
        Collection<Participant> participants;
        boolean seedingByRandom;

        public Collection<Participant> getParticipants() {
            return participants;
        }

        public void setParticipants(Collection<Participant> participants) {
            this.participants = participants;
        }

        public boolean isSeedingByRandom() {
            return seedingByRandom;
        }

        public void setSeedingByRandom(boolean seedingByRandom) {
            this.seedingByRandom = seedingByRandom;
        }
    }

    private Tournament validateTournament(long tournamentId) {
        Tournament tournament = this.tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            throw new TournamentNotFoundException(tournamentId);
        }
        return tournament;
    }
}
