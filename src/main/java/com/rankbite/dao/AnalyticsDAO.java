package com.rankbite.dao;

import com.rankbite.analytics.CategoryAnalyticsResult;
import com.rankbite.analytics.CustomerAnalyticsResult;
import com.rankbite.analytics.RestaurantAnalyticsResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsDAO {

    public double getTotalRevenue() throws SQLException {
        String query = "SELECT SUM(order_amount) AS total FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }

    public int getTotalOrders() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public int getTotalRestaurants() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM restaurants";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public double getAverageDeliveryTime() throws SQLException {
        String query = "SELECT AVG(delivery_time) AS avg_delivery FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("avg_delivery");
            }
        }
        return 0.0;
    }

    public List<RestaurantAnalyticsResult> getRestaurantAnalytics() throws SQLException {
        String query = "SELECT r.restaurant_id, r.name, " +
                       "COUNT(o.order_id) AS total_orders, " +
                       "SUM(o.order_amount) AS total_revenue, " +
                       "AVG(o.rating) AS avg_rating, " +
                       "AVG(o.delivery_time) AS avg_delivery " +
                       "FROM restaurants r LEFT JOIN orders o ON r.restaurant_id = o.restaurant_id " +
                       "GROUP BY r.restaurant_id, r.name " +
                       "ORDER BY total_revenue DESC";
                       
        List<RestaurantAnalyticsResult> results = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(new RestaurantAnalyticsResult(
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getInt("total_orders"),
                        rs.getDouble("total_revenue"),
                        rs.getDouble("avg_rating"),
                        rs.getDouble("avg_delivery")
                ));
            }
        }
        return results;
    }

    public List<CategoryAnalyticsResult> getCategoryAnalytics() throws SQLException {
        String query = "SELECT r.category, COUNT(o.order_id) AS total_orders, " +
                       "SUM(o.order_amount) AS total_revenue, AVG(o.order_amount) AS avg_price " +
                       "FROM restaurants r JOIN orders o ON r.restaurant_id = o.restaurant_id " +
                       "GROUP BY r.category ORDER BY total_orders DESC";
                       
        List<CategoryAnalyticsResult> results = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(new CategoryAnalyticsResult(
                        rs.getString("category"),
                        rs.getInt("total_orders"),
                        rs.getDouble("total_revenue"),
                        rs.getDouble("avg_price")
                ));
            }
        }
        return results;
    }

    public List<CustomerAnalyticsResult> getCustomerAnalytics() throws SQLException {
        String query = "SELECT customer_id, COUNT(order_id) AS total_orders, " +
                       "SUM(order_amount) AS total_revenue, AVG(order_amount) AS avg_order_value " +
                       "FROM orders GROUP BY customer_id ORDER BY total_orders DESC";
                       
        List<CustomerAnalyticsResult> results = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(new CustomerAnalyticsResult(
                        rs.getInt("customer_id"),
                        rs.getInt("total_orders"),
                        rs.getDouble("total_revenue"),
                        rs.getDouble("avg_order_value")
                ));
            }
        }
        return results;
    }

    public int getPeakOrderingHour() throws SQLException {
        String query = "SELECT HOUR(order_date) AS order_hour, COUNT(*) AS count " +
                       "FROM orders GROUP BY HOUR(order_date) ORDER BY count DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("order_hour");
            }
        }
        return -1; // Indicates no data
    }
}
