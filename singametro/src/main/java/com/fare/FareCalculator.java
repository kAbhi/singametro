package com.fare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fare.FareRule.*;

public class FareCalculator {

    public String dailyTotalLineString(String fromLine, String toLine) {
        return fromLine.concat("-to-").concat(toLine);
    }

    /**
     * Checks if the given dateTime is in peak hour slot as defined in the problem statement or not.
     * Peak hour
     * Monday to Friday - 8:00 to 10:00, 16:30 to 19:00
     * Saturday - 10:00 to 14:00, 18:00 to 23:00
     * Sunday - 18:00 to 23:00
     * @param dateTime
     * @return boolean - true if peak hour, false if not.
     */
    public boolean isPeakHour(LocalDateTime dateTime) {
        DayOfWeek day = dateTime.getDayOfWeek();
        int hour = dateTime.getHour();
        int min = dateTime.getMinute();

        if (day.getValue() >= 1 && day.getValue() <= 5) {
            // Monday to Friday
            // 8:00 to 10:00 OR 16:30 to 16:59 OR 17:00 to 19:00
            return (hour >= 8 && hour < 10) || (hour == 16 && min >= 30) || (hour >= 17 && hour < 19);
        } else if (day == DayOfWeek.SATURDAY) {
            // Saturday
            // 10:00 to 14:00, 18:00 to 23:00
            return (hour >= 10 && hour < 14) || (hour >= 18 && hour < 23);
        } else if (day == DayOfWeek.SUNDAY) {
            // Sunday
            // 18:00 to 23:00
            return hour >= 18 && hour < 23;
        }
        return false;
    }

    public int calculateFare(List<Journey> journeys) {
        Map<LocalDate, Map<String, Integer>> dailyTotals = new HashMap<>();
        Map<LocalDate, Map<String, Integer>> weeklyTotals = new HashMap<>();
        int totalFare = 0;

        for (Journey journey : journeys) {
            String fromLine = journey.getFromLine();
            String toLine = journey.getToLine();
            LocalDateTime dateTime = journey.getDateTime();

            boolean peak = isPeakHour(dateTime);
            int fare = FARES.get(fromLine).get(toLine)[peak ? 0 : 1];
            LocalDate day = dateTime.toLocalDate();
            LocalDate weekStart = day.minusDays(day.getDayOfWeek().getValue() - 1);

            dailyTotals.putIfAbsent(day, new HashMap<>());
            weeklyTotals.putIfAbsent(weekStart, new HashMap<>());

            String totalCalcString = dailyTotalLineString(fromLine, toLine);
            int dailyCap = DAILY_CAPS.get(totalCalcString);
            int weeklyCap = WEEKLY_CAPS.get(totalCalcString);

            dailyTotals.get(day).put(totalCalcString, dailyTotals.get(day).getOrDefault(totalCalcString, 0) + fare);
            weeklyTotals.get(weekStart).put(totalCalcString, weeklyTotals.get(weekStart).getOrDefault(totalCalcString, 0) + fare);

            if (dailyTotals.get(day).get(totalCalcString) > dailyCap) {
                fare -= dailyTotals.get(day).get(totalCalcString) - dailyCap;
                dailyTotals.get(day).put(totalCalcString, dailyCap);
            }

            if (weeklyTotals.get(weekStart).get(totalCalcString) > weeklyCap) {
                fare -= weeklyTotals.get(weekStart).get(totalCalcString) - weeklyCap;
                weeklyTotals.get(weekStart).put(totalCalcString, weeklyCap);
            }

            totalFare += Math.max(fare, 0);
        }
        return totalFare;
    }
}
