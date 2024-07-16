package com.bets.App.Controllers;

import com.bets.App.DTOs.crashBets.CreateCrashBetDTO;
import com.bets.App.Services.crashBets.CreateCrashBetService;
import com.bets.App.Models.CrashBetModel;
import com.bets.App.Repositories.CrashBetRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CrashBetController {

    @Autowired
    private static CrashBetRepository crashBetRepository;

    public static CrashBetModel createCrashBet(@NotNull CreateCrashBetDTO crashBetDTO) {
        CreateCrashBetService createCrashBetService = new CreateCrashBetService(crashBetRepository);
        return createCrashBetService.execute(crashBetDTO);
    }
}
