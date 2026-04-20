package com.mahdibou.aiAssistant.service;

import com.mahdibou.aiAssistant.tool.ScheduleVisitTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ToolRoutingService {

    private final ScheduleVisitTool scheduleVisitTool;

    public boolean shouldHandleScheduling(String message) {
        if (message == null) {
            return false;
        }

        String lower = message.toLowerCase(Locale.ROOT);

        return lower.contains("schedule")
                || lower.contains("book")
                || lower.contains("visit")
                || lower.contains("appointment");
    }

    public String tryHandleScheduling(String message) {
        String day = extractDay(message);
        String time = extractTime(message);

        if (day != null && time != null) {
            return scheduleVisitTool.scheduleVisit(day, time);
        }

        return "I can schedule a visit for you. Please provide a day and time, for example: Saturday at 10:00.";
    }

    private String extractDay(String message) {
        String[] days = {
                "monday", "tuesday", "wednesday", "thursday",
                "friday", "saturday", "sunday"
        };

        String lower = message.toLowerCase(Locale.ROOT);

        for (String day : days) {
            if (lower.contains(day)) {
                return capitalize(day);
            }
        }

        return null;
    }

    private String extractTime(String message) {
        Pattern pattern = Pattern.compile("\\b(\\d{1,2}:\\d{2})\\b");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    private String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
