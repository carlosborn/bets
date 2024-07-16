package Bet;

import com.bets.App.Controllers.CrashRoundController;
import com.bets.App.Repositories.CrashRoundRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class ConfigurationBean {

    @Bean
    public CrashRoundController crashRoundController(CrashRoundRepository crashRoundRepository) {
        return new CrashRoundController(crashRoundRepository);
    }

}
