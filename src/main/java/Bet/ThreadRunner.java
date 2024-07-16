package Bet;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationContext;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadRunner {

    public static final long DEFAULT_INTERVAL = 3500;

    public static void run(long interval, ApplicationContext applicationContext) {
        if (interval <= 0) {
            interval = DEFAULT_INTERVAL;
        }

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Watcher.watch(applicationContext);
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, interval);
    }

}
