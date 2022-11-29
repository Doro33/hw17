package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.base.entity.BaseEntity;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Match extends BaseEntity<Long> {

    public Match(Season season, int week, Team homeTeam, int homeTeamGoals, Team awayTeam, int awayTeamGoals, Stadium stadium) {
        this.season = season;
        this.week = week;
        this.homeTeam = homeTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeam = awayTeam;
        this.awayTeamGoals = awayTeamGoals;
        this.stadium = stadium;
        setPoints(homeTeamGoals,awayTeamGoals);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Season season;
    private int week;

    @OneToOne(cascade = CascadeType.ALL)
    private Team homeTeam;
    private int homeTeamGoals;
    private int homeTeamPoints;

    @OneToOne(cascade = CascadeType.ALL)
    private Team awayTeam;
    private int awayTeamGoals;
    private int awayTeamPoints;

    @ManyToOne(cascade = CascadeType.ALL)
    private Stadium stadium;

    @ManyToMany(mappedBy = "matches")
    @ToString.Exclude
    private Set<Player> players;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Scorer> scorers;

    private void setPoints(int homeTeamGoals, int awayTeamGoals) {
        if (homeTeamGoals > awayTeamGoals) {
            this.homeTeamPoints = 3;
            this.awayTeamPoints = 0;
        } else if (homeTeamGoals < awayTeamGoals) {
            this.homeTeamPoints = 0;
            this.awayTeamPoints = 3;
        } else {
            this.homeTeamPoints = 1;
            this.awayTeamPoints = 1;
        }
    }
}