package com.tpark.tournament.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_sports")
public class Sport implements Serializable {

    private static final long serialVersionUID = -4573084748046627444L;
    @Id
    @GeneratedValue
    @Column(name = "sport_id")
    private long sportId;

    @Column(name = "name")
    private String name;

    Sport() {
    }

    public Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getSportId() {
        return sportId;
    }

    @Override
    public String toString() {
        return "Sport{" + "sportId=" + sportId + ", name='" + name + '\'' + '}';
    }
}
