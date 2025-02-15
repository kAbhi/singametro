package com.fare;

import java.util.HashMap;
import java.util.Map;

public class FareRule {
    static final Map<String, Map<String, int[]>> FARES = new HashMap<>();
    static final Map<String, Integer> DAILY_CAPS = new HashMap<>();
    static final Map<String, Integer> WEEKLY_CAPS = new HashMap<>();

    static {
        FARES.put("Green", new HashMap<>());
        FARES.get("Green").put("Green", new int[]{2, 1});
        FARES.get("Green").put("Red", new int[]{4, 3});

        FARES.put("Red", new HashMap<>());
        FARES.get("Red").put("Red", new int[]{3, 2});
        FARES.get("Red").put("Green", new int[]{3, 2});

        DAILY_CAPS.put("Green-to-Green", 8);
        DAILY_CAPS.put("Red-to-Red", 12);
        DAILY_CAPS.put("Green-to-Red", 15);
        DAILY_CAPS.put("Red-to-Green", 15);

        WEEKLY_CAPS.put("Green-to-Green", 55);
        WEEKLY_CAPS.put("Red-to-Red", 70);
        WEEKLY_CAPS.put("Green-to-Red", 90);
        WEEKLY_CAPS.put("Red-to-Green", 90);
    }
}
