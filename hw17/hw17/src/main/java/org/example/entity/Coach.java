package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Coach extends BaseEntity<Long> {
    public Coach(String name) {
        this.name = name;
        this.contracts = new HashSet<>();
    }
    private String name;

    @OneToMany(mappedBy = "coach")
    @ToString.Exclude
    private Set<CoachContract> contracts;
}