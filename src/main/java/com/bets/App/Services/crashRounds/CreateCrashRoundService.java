package com.bets.App.Services.crashRounds;

import com.bets.App.DTOs.crashRounds.CreateCrashRoundDTO;
import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Repositories.CrashRoundRepository;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreateCrashRoundService {

    @NotNull
    private CrashRoundRepository repository;

    public CreateCrashRoundService(CrashRoundRepository repository) {
        this.repository = repository;
    }

    public CrashRoundModel execute(CreateCrashRoundDTO crashRoundDTO) {
        CrashRoundModel crashRoundModel = new CrashRoundModel();
        crashRoundModel.setHash(crashRoundDTO.hash());
        crashRoundModel.setStatus(crashRoundDTO.status());
        crashRoundModel.setCrashPoint(crashRoundDTO.crashPoint());
        crashRoundModel.setTotalEurBet(crashRoundDTO.totalEurBet());
        crashRoundModel.setTotalBetsPlaced(crashRoundDTO.totalBetsPlaced());
        crashRoundModel.setTotalEurWon(crashRoundDTO.totalEurWon());
        crashRoundModel.setCreatedAt(new Date());
        crashRoundModel.setUpdatedAt(new Date());

        return this.repository.save(crashRoundModel);
    }

}
