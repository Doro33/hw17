package org.example.entity;

import jakarta.persistence.*;
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
public class Transfer extends BaseEntity<Long> {
    public Transfer(TransferWindow window, Team sellerTeam, Team buyerTeam, Player player
            , double cost, LocalDate date, PlayerContract playerContract) {

        if (!(date.isAfter(window.getStartAt())
                && date.isBefore(window.getEndAt())))
            throw new RuntimeException("Transfer window is not open.");

        if(!buyerTeam.isTransferPermission())
            throw new RuntimeException("Buyer team does not have transfer permission.");

        this.transferWindow = window;
        this.sellerTeam = sellerTeam;
        this.buyerTeam = buyerTeam;
        this.player = player;
        this.cost = cost;
        this.date = date;
        this.playerContract=playerContract;

        window.getTransfers().add(this);
        sellerTeam.getSells().add(this);
        buyerTeam.getBuys().add(this);

        buyerTeam.getPlayerContracts().add(playerContract);
        player.getContracts().add(playerContract);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private TransferWindow transferWindow;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team sellerTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team buyerTeam;

    //todo: ask about this
    //@ManyToOne
    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @OneToOne(cascade = CascadeType.ALL)
    private PlayerContract playerContract;

    private double cost;

    private LocalDate date;
}