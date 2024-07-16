package com.bets.App.Repositories;

import com.bets.App.Models.CrashBetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrashBetRepository extends JpaRepository<CrashBetModel, String> {

    CrashBetModel save(CrashBetModel crashBetModel);

    CrashBetModel findByHash(String hash);

}
