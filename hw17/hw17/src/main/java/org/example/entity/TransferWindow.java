package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class TransferWindow extends BaseEntity<Long> {

    public TransferWindow(String name, Season previousSeason, Season nextSeason) {
        this.name = name;
        this.previousSeason = previousSeason;
        this.nextSeason = nextSeason;
        this.startAt = previousSeason.getEndAt().plusDays(1);
        this.endAt = nextSeason.getStartAt().minusDays(1);
        this.transfers = new HashSet<>();
    }

    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Season previousSeason;
    @OneToOne(cascade = CascadeType.ALL)
    private Season nextSeason;

    private LocalDate startAt;
    private LocalDate endAt;

    @OneToMany(mappedBy = "transferWindow")
    @ToString.Exclude
    private Set<Transfer> transfers;
}