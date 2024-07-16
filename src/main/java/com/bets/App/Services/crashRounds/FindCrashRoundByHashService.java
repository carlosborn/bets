package com.bets.App.Services.crashRounds;

import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Repositories.CrashRoundRepository;
import jakarta.validation.constraints.NotNull;

public class FindCrashRoundByHashService {

    @NotNull
    private CrashRoundRepository crashRoundRepository;

    public FindCrashRoundByHashService(CrashRoundRepository crashRoundRepository){
        this.crashRoundRepository = crashRoundRepository;
    }

    public CrashRoundModel execute(String hash){
        return this.crashRoundRepository.findByHash(hash);
    }

}
