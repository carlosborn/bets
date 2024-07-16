package com.bets.App.Services.crashBets;

import com.bets.App.DTOs.crashBets.CreateCrashBetDTO;
import com.bets.App.Models.CrashBetModel;
import com.bets.App.Repositories.CrashBetRepository;
import jakarta.validation.constraints.NotNull;

public class FindCrashBetByHashService {

    @NotNull
    private CrashBetRepository repository;

    public FindCrashBetByHashService(CrashBetRepository repository) {
        this.repository = repository;
    }

    public CrashBetModel execute(String hash) {
        return this.repository.findByHash(hash);
    }
}
