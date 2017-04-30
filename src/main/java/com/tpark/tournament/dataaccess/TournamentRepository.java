package com.tpark.tournament.dataaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.Query;

/**
 * JPA Respository interface for 'tournaments' table.
 *
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    public Collection<Tournament> findByNameIgnoreCaseContaining(String name);
}
