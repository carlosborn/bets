package com.bets.App.Repositories;

import com.bets.App.Models.CrashRoundModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrashRoundRepository extends JpaRepository<CrashRoundModel, String> {

    CrashRoundModel save(CrashRoundModel crashRoundModel);

    CrashRoundModel findByHash(String hash);

}
