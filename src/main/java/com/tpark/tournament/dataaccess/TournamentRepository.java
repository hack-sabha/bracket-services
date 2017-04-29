package com.tpark.tournament.dataaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Tournament;

/**
 * JPA Respository interface for 'tournaments' table.
 *
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    public Collection<Tournament> findByName(String name);
}
