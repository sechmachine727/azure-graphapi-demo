package fu.capstone.outlookintegrationdemo.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTasks {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + new java.util.Date());
    }
}
