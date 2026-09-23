package com.rankbite.service;

import com.rankbite.analytics.CategoryAnalyticsResult;
import com.rankbite.analytics.CustomerAnalyticsResult;
import com.rankbite.analytics.RestaurantAnalyticsResult;
import com.rankbite.dao.AnalyticsDAO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AnalyticsService {
    private AnalyticsDAO analyticsDAO;

    public AnalyticsService() {
        this.analyticsDAO = new AnalyticsDAO();
    }
    
    // For testing/mocking
    public AnalyticsService(AnalyticsDAO analyticsDAO) {
        this.analyticsDAO = analyticsDAO;
    }

    public double getTotalRevenue() {
        try {
            return analyticsDAO.getTotalRevenue();
        } catch (SQLException e) {
            System.err.println("Error fetching total revenue: " + e.getMessage());
            return 0.0;
        }
    }

    public int getTotalOrders() {
        try {
            return analyticsDAO.getTotalOrders();
        } catch (SQLException e) {
            System.err.println("Error fetching total orders: " + e.getMessage());
            return 0;
        }
    }

    public int getTotalRestaurants() {
        try {
            return analyticsDAO.getTotalRestaurants();
        } catch (SQLException e) {
            System.err.println("Error fetching total restaurants: " + e.getMessage());
            return 0;
        }
    }

    public double getAverageDeliveryTime() {
        try {
            return analyticsDAO.getAverageDeliveryTime();
        } catch (SQLException e) {
            System.err.println("Error fetching average delivery time: " + e.getMessage());
            return 0.0;
        }
    }

    public List<RestaurantAnalyticsResult> getRestaurantAnalytics() {
        try {
            return analyticsDAO.getRestaurantAnalytics();
        } catch (SQLException e) {
            System.err.println("Error fetching restaurant analytics: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<CategoryAnalyticsResult> getCategoryAnalytics() {
        try {
            return analyticsDAO.getCategoryAnalytics();
        } catch (SQLException e) {
            System.err.println("Error fetching category analytics: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<CustomerAnalyticsResult> getCustomerAnalytics() {
        try {
            return analyticsDAO.getCustomerAnalytics();
        } catch (SQLException e) {
            System.err.println("Error fetching customer analytics: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public int getPeakOrderingHour() {
        try {
            return analyticsDAO.getPeakOrderingHour();
        } catch (SQLException e) {
            System.err.println("Error fetching peak ordering hour: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Calculates the percentile of a list of values.
     * Formula used: index = ceil((percentile / 100) * N) - 1
     * @param values The list of integers.
     * @param percentile The desired percentile (0 to 100).
     * @return The value at the calculated percentile, or 0 if list is empty.
     */
    public double calculatePercentile(List<Integer> values, double percentile) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        if (values.size() == 1) {
            return values.get(0);
        }
        
        Collections.sort(values);
        
        if (percentile <= 0) return values.get(0);
        if (percentile >= 100) return values.get(values.size() - 1);
        
        int index = (int) Math.ceil((percentile / 100.0) * values.size()) - 1;
        if (index < 0) {
            index = 0;
        }
        
        return values.get(index);
    }
}
