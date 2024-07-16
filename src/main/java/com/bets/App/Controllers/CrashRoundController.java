package com.bets.App.Controllers;

import com.bets.App.DTOs.crashRounds.CreateCrashRoundDTO;
import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Repositories.CrashRoundRepository;
import com.bets.App.Services.crashRounds.CreateCrashRoundService;
import com.bets.App.Services.crashRounds.FindCrashRoundByHashService;
import com.bets.App.Services.crashRounds.UpdateCrashRoundService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CrashRoundController {

    private CrashRoundRepository crashRoundRepository;

    public CrashRoundModel createCrashRound(CreateCrashRoundDTO crashRoundDTO) {
        CreateCrashRoundService createCrashRoundService = new CreateCrashRoundService(crashRoundRepository);
        return createCrashRoundService.execute(crashRoundDTO);
    }

    public CrashRoundModel getCrashRoundByHash(String hash) {
        FindCrashRoundByHashService findCrashRoundByHash = new FindCrashRoundByHashService(crashRoundRepository);
        return findCrashRoundByHash.execute(hash);
    }

    public CrashRoundModel updateCrashRound(CrashRoundModel crashRoundModel) {
        UpdateCrashRoundService updateCrashRoundService = new UpdateCrashRoundService(crashRoundRepository);
        return updateCrashRoundService.execute(crashRoundModel);
    }

}
