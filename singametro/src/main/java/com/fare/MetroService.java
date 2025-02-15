package com.fare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MetroService {
    // Ideally Lombok to be used for all constructors, getters, setters, etc.
    public MetroService() {
    }

    public List<Journey> readJourneysFromFile(String filePath) throws IOException {
        List<Journey> journeys = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.split(",");
            journeys.add(new Journey(parts[0], parts[1], parts[2]));
        }
        return journeys;
    }
}
