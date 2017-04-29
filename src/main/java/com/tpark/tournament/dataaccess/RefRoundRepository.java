package com.tpark.tournament.dataaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Round;

/**
 * JPA Respository interface for 'ref_round' table.
 * 
 */
public interface RefRoundRepository extends JpaRepository<Round, Long> {
    public Collection<Round> findByRoundName(String roundName);
}
