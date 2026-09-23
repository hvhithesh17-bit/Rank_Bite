package com.rankbite.util;

import com.rankbite.exception.InvalidDeliveryTimeException;
import com.rankbite.exception.InvalidPriceException;
import com.rankbite.exception.InvalidRatingException;
import com.rankbite.model.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Restaurant> readRestaurants(String filePath) {
        List<Restaurant> restaurants = new ArrayList<>();
        
        // Using try-with-resources to ensure file gets closed
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length < 8) {
                    System.err.println("Invalid row (not enough columns): " + line);
                    continue;
                }

                try {
                    int restaurantId = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    String category = values[2].trim();
                    String location = values[3].trim();
                    double rating = Double.parseDouble(values[4].trim());
                    int averageDeliveryTime = Integer.parseInt(values[5].trim());
                    double averagePrice = Double.parseDouble(values[6].trim());
                    int orderCount = Integer.parseInt(values[7].trim());

                    // Validate
                    DataValidator.validateRating(rating);
                    DataValidator.validateDeliveryTime(averageDeliveryTime);
                    DataValidator.validatePrice(averagePrice);

                    Restaurant restaurant = new Restaurant(restaurantId, name, category, location, 
                                                           rating, averageDeliveryTime, averagePrice, orderCount);
                    restaurants.add(restaurant);

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric value in row: " + line);
                } catch (InvalidRatingException | InvalidDeliveryTimeException | InvalidPriceException e) {
                    System.err.println("Validation failed for row: " + line + " - Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }

        return restaurants;
    }
}
