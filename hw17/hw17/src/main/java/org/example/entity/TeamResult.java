package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.base.entity.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TeamResult extends BaseEntity<Long> {
    public TeamResult(Team team, Season season,Integer points) {
        this.team = team;
        this.season = season;
        this.points=points;
        //todo: we should make a list of match of one team in a season and calculate other stuff.
        //i think we cant work with set. or maybe we better not.
        team.getSeasonsResults().add(this);
        season.getTeamsResults().add(this);
    }

    @ManyToOne
    private  Team team;
    @ManyToOne
    private Season season;

    private Integer points;

    @Override
    public String toString() {
        return "team: " + team.getName() +
                ", season: " + season.getName() +
                ", points: " + points
                +" \n";
    }
}
//private  String teamName;
/* private int matches;
    private int won;
    private int draw;
    private int lose;

    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;*/