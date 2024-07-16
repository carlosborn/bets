package com.bets.App.DTOs.crashRounds;

public record CreateCrashRoundDTO(String hash, String status, Double crashPoint, Double totalEurBet,
                                  Double totalBetsPlaced, Double totalEurWon) {
}
