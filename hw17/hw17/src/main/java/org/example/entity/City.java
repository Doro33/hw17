package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City extends BaseEntity<Long> {
    public City(String name) {
        this.name = name.toUpperCase();
        this.teams = new HashSet<>();
        this.stadiums = new HashSet<>();
    }

    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Team> teams;

    @OneToMany(mappedBy = "city")
    private Set<Stadium> stadiums;

    @Override
    public String toString() {
        return name;
    }
}