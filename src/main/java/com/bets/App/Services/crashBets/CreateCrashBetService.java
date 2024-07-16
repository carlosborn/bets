package com.bets.App.Services.crashBets;

import com.bets.App.DTOs.crashBets.CreateCrashBetDTO;
import com.bets.App.Models.CrashBetModel;
import com.bets.App.Repositories.CrashBetRepository;
import jakarta.validation.constraints.NotNull;

public class CreateCrashBetService {

    @NotNull
    private CrashBetRepository repository;

    public CreateCrashBetService(CrashBetRepository repository) {
        this.repository = repository;
    }

    public CrashBetModel execute(CreateCrashBetDTO crashBetDTO) {
        CrashBetModel crashBetModel = new CrashBetModel();
        crashBetModel.setHash(crashBetDTO.hash());
        crashBetModel.setCashedOutAt(crashBetDTO.cashedOutAt());
        crashBetModel.setAmount(crashBetDTO.amount());
        crashBetModel.setCurrencyType(crashBetDTO.currencyType());
        crashBetModel.setAutoCashoutAt(crashBetDTO.autoCashoutAt());
        crashBetModel.setWinAmount(crashBetDTO.winAmount());
        crashBetModel.setStatus(crashBetDTO.status());
        crashBetModel.setUserModel(crashBetDTO.userModel());
        crashBetModel.setCrashRoundModel(crashBetDTO.crashRoundModel());

        return this.repository.save(crashBetModel);
    }

}
