package com.school_managment.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SchoolTaskScheduler {

   // it will run for every 30 sec
    @Scheduled(fixedRate = 30000)
    public void runHeartbeatTask() {
        System.out.println("[Scheduler] Heartbeat running at: " + LocalDateTime.now());
    }
}
