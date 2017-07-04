package com.tpark.tournament.dataaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Sport;

/**
 * JPA Respository interface for 'ref_sport' table.
 */
public interface RefSportRepository extends JpaRepository<Sport, Long> {
    public Collection<Sport> findByName(String name);
}
