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
@Table(name = "tournament_teams")
public class Team  implements Serializable {
    private static final long serialVersionUID = 3363957270678118151L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "seed")
    private Long seed;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public Long getSeed() {
        return seed;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Team(long teamId, String teamName, Long seed, Tournament tournament) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.seed = seed;
        this.tournament = tournament;
    }

    Team() {
    }

    @Override
    public String toString() {
        return "Team{" + "teamId=" + teamId + ", teamName='" + teamName + '\'' + ", seed=" + seed + ", tournament=" + tournament + '}';
    }
}
