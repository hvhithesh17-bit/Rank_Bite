package com.rankbite.ranking;

import com.rankbite.model.Restaurant;
import java.util.List;

public class RankingData {
    private int minDeliveryTime;
    private int maxDeliveryTime;
    private double minPrice;
    private double maxPrice;
    private int minDemand;
    private int maxDemand;

    public void calculateMinMax(List<Restaurant> restaurants) {
        if (restaurants == null || restaurants.isEmpty()) {
            return;
        }

        minDeliveryTime = Integer.MAX_VALUE;
        maxDeliveryTime = Integer.MIN_VALUE;
        minPrice = Double.MAX_VALUE;
        maxPrice = Double.MIN_VALUE;
        minDemand = Integer.MAX_VALUE;
        maxDemand = Integer.MIN_VALUE;

        for (Restaurant r : restaurants) {
            if (r.getAverageDeliveryTime() < minDeliveryTime) minDeliveryTime = r.getAverageDeliveryTime();
            if (r.getAverageDeliveryTime() > maxDeliveryTime) maxDeliveryTime = r.getAverageDeliveryTime();

            if (r.getAveragePrice() < minPrice) minPrice = r.getAveragePrice();
            if (r.getAveragePrice() > maxPrice) maxPrice = r.getAveragePrice();

            if (r.getOrderCount() < minDemand) minDemand = r.getOrderCount();
            if (r.getOrderCount() > maxDemand) maxDemand = r.getOrderCount();
        }
    }

    public int getMinDeliveryTime() { return minDeliveryTime; }
    public int getMaxDeliveryTime() { return maxDeliveryTime; }
    public double getMinPrice() { return minPrice; }
    public double getMaxPrice() { return maxPrice; }
    public int getMinDemand() { return minDemand; }
    public int getMaxDemand() { return maxDemand; }
}
