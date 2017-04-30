package com.tpark.tournament.dataaccess;

import com.tpark.tournament.entity.Round;
import com.tpark.tournament.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * JPA Respository interface for 'ref_sport' table.
 * 
 */
public interface RefSportRepository extends JpaRepository<Sport, Long> {
    public Collection<Sport> findByName(String name);
}
