package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.base.entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlayerContract extends BaseEntity<Long> {
    public PlayerContract(Team team, Player player,Season season,double salary) {
        if (!team.isTransferPermission())
            throw new RuntimeException("Team does not have transfer permission.");

        this.team = team;
        this.player = player;
        this.season=season;
        this.salary = salary;
        //this.isActive = true;

        team.getPlayerContracts().add(this);
        player.getContracts().add(this);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    private Season season;

    private double salary;
}

    /*private LocalDate startAt;
    private LocalDate endAt;
    private LocalDate terminateAt;

    // this field was made because of better performance in DB.
    // I thought working with boolean is better than LocalDate.
    private boolean isActive;

    public void terminate(LocalDate date) {
        this.isActive = false;
        this.terminateAt = date;
    }
    */