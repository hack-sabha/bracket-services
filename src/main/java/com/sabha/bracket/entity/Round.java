/**
 * @author Swaroop Gaddameedhi.
 */
package com.sabha.bracket.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity object for 'ref_round' table.
 *
 * @author Swaroop Gaddameedhi
 */
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
