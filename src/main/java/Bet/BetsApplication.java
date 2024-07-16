package Bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.bets.App")
@EnableJpaRepositories("com.bets.App.Repositories")
@EntityScan("com.bets.App.Models")
public class BetsApplication {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(BetsApplication.class, args);
    }

    @Bean
    public ApplicationRunner runThreads(ApplicationContext applicationContext) {
        return args -> {
            ThreadRunner.run(ThreadRunner.DEFAULT_INTERVAL, applicationContext);
        };
    }

}
