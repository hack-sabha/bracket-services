package com.tpark.tournament.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_tournament_types")
public class RefTournamentType implements Serializable {

    private static final long serialVersionUID = -41408983083910280L;
    @Id
    @GeneratedValue
    @Column(name = "tournament_type_id")
    private long tournamentTypeId;

    @Column(name = "name")
    private String name;

    RefTournamentType() {
    }

    public RefTournamentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getTournamentTypeId() {
        return tournamentTypeId;
    }

    @Override
    public String toString() {
        return "TournamentType{" + "tournamentTypeId=" + tournamentTypeId + ", name='" + name + '\'' + '}';
    }
}