package com.sabha.bracket.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity for 'bracket' table.
 *
 * @author Swaroop Gaddameedhi
 */
@Entity
@Table(name = "brackets")
public class Bracket implements Serializable {
    private static final long serialVersionUID = -1347292377449136017L;

    @Id
    @GeneratedValue
    @Column(name = "bracket_id")
    private long bracketId;

    private String name;
    private int type;

    @OneToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @Column(name = "participant_count")
    private int participantCount;


    Bracket() {
    }

    public Bracket(String name, int type, Sport sport, int participantCount) {
        this.name = name;
        this.type = type;
        this.sport = sport;
        this.participantCount = participantCount;
    }

    public long getBracketId() {
        return bracketId;
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
        return String.format("Bracket[id=%d name=%s type= sport=%s partipantCount=%d]", bracketId, name, type, sport.getName(), participantCount);
    }
}
