package Bet;

import com.bets.App.Controllers.CrashBetController;
import com.bets.App.Controllers.CrashRoundController;
import com.bets.App.Controllers.UserController;
import com.bets.App.Repositories.CrashBetRepository;
import com.bets.App.Repositories.CrashRoundRepository;
import com.bets.App.Repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class ConfigurationBean {

    @Bean
    public CrashRoundController crashRoundController(CrashRoundRepository crashRoundRepository) {
        return new CrashRoundController(crashRoundRepository);
    }

    @Bean
    public CrashBetController crashBetController(CrashBetRepository crashBetRepository) {
        return new CrashBetController(crashBetRepository);
    }

    @Bean
    public UserController userController(UserRepository userRepository) {
        return new UserController(userRepository);
    }

}
