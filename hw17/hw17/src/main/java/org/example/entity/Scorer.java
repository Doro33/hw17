package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.base.entity.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Scorer extends BaseEntity<Long> {
    public Scorer(Player player, int goalNumbers, Match match) {
        this.player = player;
        this.goalNumbers = goalNumbers;
        this.match = match;

        match.getScorers().add(this);
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;
    private int goalNumbers;

    @ManyToOne(cascade = CascadeType.ALL)
    private Match match;
}