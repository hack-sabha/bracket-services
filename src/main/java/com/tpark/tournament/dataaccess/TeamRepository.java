package com.tpark.tournament.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpark.tournament.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
