package com.rankbite.analytics;

public class RestaurantAnalyticsResult {
    private int restaurantId;
    private String name;
    private int totalOrders;
    private double totalRevenue;
    private double averageRating;
    private double averageDeliveryTime;

    public RestaurantAnalyticsResult(int restaurantId, String name, int totalOrders, 
                                     double totalRevenue, double averageRating, double averageDeliveryTime) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.averageRating = averageRating;
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public int getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public int getTotalOrders() { return totalOrders; }
    public double getTotalRevenue() { return totalRevenue; }
    public double getAverageRating() { return averageRating; }
    public double getAverageDeliveryTime() { return averageDeliveryTime; }

    @Override
    public String toString() {
        return "Restaurant: " + name + " | Orders: " + totalOrders + " | Revenue: " + totalRevenue + 
               " | Avg Rating: " + averageRating + " | Avg Delivery: " + averageDeliveryTime;
    }
}
