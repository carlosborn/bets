package com.bets.App.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "crash_bets")
public class CrashBetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "cashed_out_at")
    private Double cashedOutAt;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency_type")
    private String currencyType;

    @Column(name = "auto_cashout_at")
    private Double autoCashoutAt;

    @Column(name = "win_amount")
    private Double winAmount;

    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne(targetEntity = CrashRoundModel.class)
    @JoinColumn(name = "round_id")
    private CrashRoundModel crashRoundModel;
}