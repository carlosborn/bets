package com.bets.App.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "crash_rounds")
public class CrashRoundModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "status")
    private String status;

    @Column(name = "crash_point")
    private Double crashPoint;

    @Column(name = "total_eur_bet")
    private Double totalEurBet;

    @Column(name = "total_bets_placed")
    private Double totalBetsPlaced;

    @Column(name = "total_eur_won")
    private Double totalEurWon;

    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}