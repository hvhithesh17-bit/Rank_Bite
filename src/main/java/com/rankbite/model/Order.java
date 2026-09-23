package com.rankbite.model;

import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int customerId;
    private int restaurantId;
    private int foodId;
    private int quantity;
    private double orderAmount;
    private double rating;
    private int deliveryTime;
    private double distance;
    private LocalDateTime orderDateTime;

    public Order(int orderId, int customerId, int restaurantId, int foodId, int quantity, 
                 double orderAmount, double rating, int deliveryTime, double distance, 
                 LocalDateTime orderDateTime) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.orderAmount = orderAmount;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.distance = distance;
        this.orderDateTime = orderDateTime;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getOrderAmount() { return orderAmount; }
    public void setOrderAmount(double orderAmount) { this.orderAmount = orderAmount; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(int deliveryTime) { this.deliveryTime = deliveryTime; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }
}
