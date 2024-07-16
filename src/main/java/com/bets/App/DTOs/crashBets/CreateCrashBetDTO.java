package com.bets.App.DTOs.crashBets;

import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Models.UserModel;

public record CreateCrashBetDTO(String hash, Double cashedOutAt, Double amount, String currencyType,
                                Double autoCashoutAt, Double winAmount, String status, UserModel userModel, CrashRoundModel crashRoundModel) {
}
