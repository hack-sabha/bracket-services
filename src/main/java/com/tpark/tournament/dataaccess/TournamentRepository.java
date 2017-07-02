package com.tpark.tournament.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Tournament;

/**
 * JPA Respository interface for 'tournaments' table.
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    public List<Tournament> findByNameIgnoreCaseContaining(String name);
}
