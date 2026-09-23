package com.rankbite.model;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String category;
    private String location;
    private double rating;
    private int averageDeliveryTime;
    private double averagePrice;
    private int orderCount;

    public Restaurant(int restaurantId, String name, String category, String location, 
                      double rating, int averageDeliveryTime, double averagePrice, int orderCount) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.category = category;
        this.location = location;
        this.rating = rating;
        this.averageDeliveryTime = averageDeliveryTime;
        this.averagePrice = averagePrice;
        this.orderCount = orderCount;
    }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getAverageDeliveryTime() { return averageDeliveryTime; }
    public void setAverageDeliveryTime(int averageDeliveryTime) { this.averageDeliveryTime = averageDeliveryTime; }

    public double getAveragePrice() { return averagePrice; }
    public void setAveragePrice(double averagePrice) { this.averagePrice = averagePrice; }

    public int getOrderCount() { return orderCount; }
    public void setOrderCount(int orderCount) { this.orderCount = orderCount; }

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}
