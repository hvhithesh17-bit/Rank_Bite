package com.rankbite.analytics;

public class CustomerAnalyticsResult {
    private int customerId;
    private int totalOrders;
    private double totalRevenue;
    private double averageOrderValue;

    public CustomerAnalyticsResult(int customerId, int totalOrders, double totalRevenue, double averageOrderValue) {
        this.customerId = customerId;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.averageOrderValue = averageOrderValue;
    }

    public int getCustomerId() { return customerId; }
    public int getTotalOrders() { return totalOrders; }
    public double getTotalRevenue() { return totalRevenue; }
    public double getAverageOrderValue() { return averageOrderValue; }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + " | Orders: " + totalOrders + " | Revenue: " + totalRevenue;
    }
}
