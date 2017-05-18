package com.tpark.tournament.dataaccess;

import com.tpark.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Respository interface for 'tournaments' table.
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    public List<Tournament> findByNameIgnoreCaseContaining(String name);
}
