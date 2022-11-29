package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.base.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Team extends BaseEntity<Long> {

    public Team(String name, City city) {
        this.name = name;
        this.city = city;
        this.transferPermission = true;
        this.coachContracts = new HashSet<>();
        this.playerContracts = new HashSet<>();
        this.seasonsResults = new HashSet<>();
        this.sells = new HashSet<>();
        this.buys = new HashSet<>();

        city.getTeams().add(this);
    }

    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

    private boolean transferPermission;

    @OneToMany(mappedBy = "team")
    @ToString.Exclude
    private Set<CoachContract> coachContracts;

    @OneToMany(mappedBy = "team")
    @ToString.Exclude
    private Set<PlayerContract> playerContracts;

    @OneToMany(mappedBy = "team")
    @ToString.Exclude
    private Set<TeamResult> seasonsResults;

    @OneToMany(mappedBy = "sellerTeam")
    @ToString.Exclude
    private Set<Transfer> sells;

    @OneToMany(mappedBy = "buyerTeam")
    @ToString.Exclude
    private Set<Transfer> buys;

    public void disablePermission() {
        this.transferPermission = false;
    }

    public void enablePermission() {
        this.transferPermission = true;
    }
}