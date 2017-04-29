/**
 * @author Swaroop Gaddameedhi.
 */
package com.tpark.tournament.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_rounds")
public class Round implements Serializable {
    private static final long serialVersionUID = -5567199720265976935L;
    @Id
    @GeneratedValue
    @Column(name = "round_id")
    private long roundId;

    @Column(name = "round_name")
    private String roundName;

    Round() {
    }

    public Round(String name) {
        this.roundName = name;
    }

    public String getRoundName() {
        return roundName;
    }

    public long getRoundId() {
        return roundId;
    }

    @Override
    public String toString() {
        return String.format("Round[id=%d name=%s]", roundId, roundName);
    }
}
