package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.base.entity.BaseEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Season extends BaseEntity<Long> {
    public Season(String name, LocalDate startAt, LocalDate endAt) {
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.teamsResults = new HashSet<>();
        this.matches = new HashSet<>();
    }

    private String name;
    private LocalDate startAt;
    private LocalDate endAt;

    @OneToMany(mappedBy = "season")
    @ToString.Exclude
    private Set<TeamResult> teamsResults;
    @OneToMany(mappedBy = "season")
    @ToString.Exclude
    private Set<Match> matches;
}
    //todo : add this to match constructor.
    /*public void addMatch(Match match) {
        matches.add(match);
    }*/