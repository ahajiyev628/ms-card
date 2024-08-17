package org.example.spring.scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.spring.service.CardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardScheduler {
    private final CardService cardService;

    // fixed time can be set for fixedDelayString using cron
    @Scheduled(fixedDelayString = "PT1M") // every 1 minute
    @SchedulerLock(name = "test", lockAtLeastFor = "PT1M", lockAtMostFor = "PT5M")
    public void cardSchedule() {
        // The scheduler should not contain any business logic,]
        // Instead, it should call the method from Service which contains the business logic, and schedule that logic
        cardService.test();
    }
}
