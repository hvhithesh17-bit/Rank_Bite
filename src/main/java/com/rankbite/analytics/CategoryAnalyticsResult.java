package com.rankbite.analytics;

public class CategoryAnalyticsResult {
    private String category;
    private int totalOrders;
    private double totalRevenue;
    private double averagePrice;

    public CategoryAnalyticsResult(String category, int totalOrders, double totalRevenue, double averagePrice) {
        this.category = category;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.averagePrice = averagePrice;
    }

    public String getCategory() { return category; }
    public int getTotalOrders() { return totalOrders; }
    public double getTotalRevenue() { return totalRevenue; }
    public double getAveragePrice() { return averagePrice; }

    @Override
    public String toString() {
        return "Category: " + category + " | Orders: " + totalOrders + " | Revenue: " + totalRevenue;
    }
}
