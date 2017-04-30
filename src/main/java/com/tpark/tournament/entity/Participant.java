package com.tpark.tournament.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tournament_participants")
public class Participant implements Serializable {
    private static final long serialVersionUID = 3246458384894783193L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private long participantId;

    @Column(name = "participant_name")
    private String participantName;

    @Column(name = "seed")
    private Long seed;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    Participant() {
    } // for JPA

    public long getParticipantId() {
        return participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public Long getSeed() {
        return seed;
    }

    public Team getTeam() {
        return team;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Participant(String name, Tournament tournament, Team team, Long seed) {
        this.participantName = name;
        this.tournament = tournament;
        this.team = team;
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Participant{" + "participantId=" + participantId + ", participantName='" + participantName + '\'' + ", seed=" + seed + ", team=" + team + ", tournament=" + tournament + '}';
    }

}
