package com.tpark.tournament.dataaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Collection<Participant> findByTournamentId(long tournamentId);

}
