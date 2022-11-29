package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Player extends BaseEntity<Long> {
    public Player(String name) {
        this.name = name;
        this.contracts = new HashSet<>();
        this.matches = new HashSet<>();
    }
    private String name;
    private String birthplace;
    private LocalDate birthday;
    private float height;
    private boolean rightFooted;
    private String position;

    @OneToMany(mappedBy = "player")
    @ToString.Exclude
    private Set<PlayerContract> contracts;

    @ManyToMany
    @ToString.Exclude
    private Set<Match> matches;
}