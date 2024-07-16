package com.bets.App.Controllers;

import com.bets.App.DTOs.crashBets.CreateCrashBetDTO;
import com.bets.App.Services.crashBets.CreateCrashBetService;
import com.bets.App.Models.CrashBetModel;
import com.bets.App.Repositories.CrashBetRepository;
import com.bets.App.Services.crashBets.FindCrashBetByHashService;
import com.bets.App.Services.crashBets.UpdateCrashBetService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CrashBetController {

    @Autowired
    private CrashBetRepository crashBetRepository;

    public CrashBetModel createCrashBet(@NotNull CreateCrashBetDTO crashBetDTO) {
        CreateCrashBetService createCrashBetService = new CreateCrashBetService(crashBetRepository);
        return createCrashBetService.execute(crashBetDTO);
    }

    public CrashBetModel findCrashBetByHash(@NotNull String hash) {
        FindCrashBetByHashService findCrashBetByHashService = new FindCrashBetByHashService(crashBetRepository);
        return findCrashBetByHashService.execute(hash);
    }

    public CrashBetModel update(@NotNull CrashBetModel crashBetModel) {
        UpdateCrashBetService updateCrashBetService = new UpdateCrashBetService(crashBetRepository);
        return updateCrashBetService.execute(crashBetModel);
    }
}
