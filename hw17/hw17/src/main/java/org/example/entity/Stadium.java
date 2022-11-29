package org.example.entity;

import jakarta.persistence.*;
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
public class Stadium extends BaseEntity<Long> {
    public Stadium(String name, City city) {
        this.name = name;
        this.capacity = 20000;
        this.city = city;

        city.getStadiums().add(this);
    }

    private String name;
    private int capacity;
    @ManyToOne(cascade = CascadeType.ALL)
    private City city;
}