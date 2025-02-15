package com.fare;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Journey {

    // Ideally Lombok to be used for all constructors, getters, setters, etc.
    String fromLine;
    String toLine;
    LocalDateTime dateTime;

    public Journey(String fromLine, String toLine, String dateTimeStr) {
        this.fromLine = fromLine;
        this.toLine = toLine;
        this.dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public String getFromLine() {
        return fromLine;
    }

    public String getToLine() {
        return toLine;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
