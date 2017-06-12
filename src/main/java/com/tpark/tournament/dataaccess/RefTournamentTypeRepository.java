package com.tpark.tournament.dataaccess;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.RefTournamentType;

public interface RefTournamentTypeRepository extends JpaRepository<RefTournamentType, Long> {
    public Collection<RefTournamentType> findByName(String name);
}