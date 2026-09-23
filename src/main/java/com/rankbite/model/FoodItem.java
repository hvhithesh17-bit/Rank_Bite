package com.rankbite.model;

public class FoodItem {
    private int foodId;
    private int restaurantId;
    private String foodName;
    private String category;
    private double price;

    public FoodItem(int foodId, int restaurantId, String foodName, String category, double price) {
        this.foodId = foodId;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.category = category;
        this.price = price;
    }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
