package com.tpark.tournament.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament implements Serializable {
    private static final long serialVersionUID = -1347292377449136017L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private long tournamentId;

    private String name;
    private int type;

    @OneToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @Column(name = "participant_count")
    private int participantCount;

    Tournament() {
    }

    public Tournament(String name, int type, Sport sport, int participantCount) {
        this.name = name;
        this.type = type;
        this.sport = sport;
        this.participantCount = participantCount;
    }

    public long getTournamentId() {
        return tournamentId;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public Sport getSport() {
        return sport;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    @Override
    public String toString() {
        return String.format("Tournament[id=%d name=%s type= sport=%s partipantCount=%d]", tournamentId, name, type, sport.getName(), participantCount);
    }
}
