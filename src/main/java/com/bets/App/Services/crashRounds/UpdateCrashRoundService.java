package com.bets.App.Services.crashRounds;

import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Repositories.CrashRoundRepository;
import jakarta.validation.constraints.NotNull;

public class UpdateCrashRoundService {

    @NotNull
    private CrashRoundRepository crashRoundRepository;

    public UpdateCrashRoundService(CrashRoundRepository crashRoundRepository){
        this.crashRoundRepository = crashRoundRepository;
    }

    public CrashRoundModel execute(CrashRoundModel crashRoundModel){
        return this.crashRoundRepository.save(crashRoundModel);
    }

}
