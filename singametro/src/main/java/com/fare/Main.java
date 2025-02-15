package com.fare;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        MetroService metroService = new MetroService();
        FareCalculator fareCalculator = new FareCalculator();

        List<Journey> journeys = metroService.readJourneysFromFile("src/main/resources/journeys.csv");
        int totalFare = fareCalculator.calculateFare(journeys);
        System.out.println("Total Fare: " + totalFare);

        List<Journey> journeys2 = metroService.readJourneysFromFile("src/main/resources/journeys2-exceed-cap.csv");
        int totalFare2 = fareCalculator.calculateFare(journeys2);
        System.out.println("Total Fare - 2 : " + totalFare2);

    }
}
