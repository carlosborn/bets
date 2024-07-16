package com.bets.App.Services.crashBets;

import com.bets.App.Models.CrashBetModel;
import com.bets.App.Repositories.CrashBetRepository;
import jakarta.validation.constraints.NotNull;

public class UpdateCrashBetService {

    @NotNull
    private CrashBetRepository repository;

    public UpdateCrashBetService(CrashBetRepository repository) {
        this.repository = repository;
    }

    public CrashBetModel execute(CrashBetModel crashBetModel) {
        return this.repository.save(crashBetModel);
    }

}
