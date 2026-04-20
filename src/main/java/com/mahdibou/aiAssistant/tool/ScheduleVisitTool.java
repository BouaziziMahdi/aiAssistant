package com.mahdibou.aiAssistant.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleVisitTool {

    @Tool(description = "Schedule a dog adoption visit using a day and a time")
    public String scheduleVisit(String day, String time) {
        log.info("Scheduling visit for day={} time={}", day, time);
        return "Your adoption visit has been scheduled for " + day + " at " + time + ".";
    }
}
