package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class CoachContract extends BaseEntity<Long> {

    public CoachContract(Team team, Coach coach,Season season, double salary) {
        if(!team.isTransferPermission())
            throw new RuntimeException("Team does not have transfer permission.");

        this.team = team;
        this.coach = coach;
        this.season = season;
        this.salary = salary;
        //this.isActive = true;

        team.getCoachContracts().add(this);
        coach.getContracts().add(this);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    private Coach coach;

    @ManyToOne(cascade = CascadeType.ALL)
    private Season season;

    private Double salary;


}

  /*  private LocalDate startAt;
    private LocalDate endAt;
    private LocalDate terminateAt;
    private boolean isActive;

    public void terminate() {
        this.isActive = false;
        this.terminateAt = LocalDate.now();
    }*/