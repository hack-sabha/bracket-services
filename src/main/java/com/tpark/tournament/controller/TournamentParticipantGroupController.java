package com.tpark.tournament.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tpark.tournament.dataaccess.ParticipantRepository;
import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Participant;
import com.tpark.tournament.entity.Tournament;
import com.tpark.tournament.resource.ParticipantGroupResource;

@RestController
@EnableAutoConfiguration
@RequestMapping("/tournament/{tournamentId}/participants")
public class TournamentParticipantGroupController {
    private static Logger LOGGER = Logger.getLogger(TournamentParticipantGroupController.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tournament> add(@PathVariable long tournamentId, @RequestBody ParticipantGroup participantGroup) {

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

    @RequestMapping(method = RequestMethod.GET)
    public ParticipantGroupResource read(@PathVariable long tournamentId) {
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
