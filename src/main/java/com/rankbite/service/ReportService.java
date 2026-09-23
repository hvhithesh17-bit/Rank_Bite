package com.rankbite.service;

import com.rankbite.analytics.CategoryAnalyticsResult;
import com.rankbite.analytics.RestaurantAnalyticsResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportService {
    
    private AnalyticsService analyticsService;

    public ReportService(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    public String generateOverallReportText() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("========================================\n");
        sb.append("RANKBITE OVERALL ANALYTICS REPORT\n");
        sb.append("========================================\n");
        sb.append("Generated on: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");
        
        sb.append("Total Restaurants: ").append(analyticsService.getTotalRestaurants()).append("\n");
        sb.append("Total Orders: ").append(analyticsService.getTotalOrders()).append("\n");
        sb.append(String.format("Total Revenue: ₹%.2f\n", analyticsService.getTotalRevenue()));
        sb.append(String.format("Average Delivery Time: %.1f minutes\n", analyticsService.getAverageDeliveryTime()));
        
        int peakHour = analyticsService.getPeakOrderingHour();
        if (peakHour != -1) {
            sb.append(String.format("Peak Ordering Hour: %02d:00\n", peakHour));
        }

        sb.append("\n----------------------------------------\n");
        sb.append("RESTAURANT PERFORMANCE\n");
        sb.append("----------------------------------------\n");
        
        List<RestaurantAnalyticsResult> restResults = analyticsService.getRestaurantAnalytics();
        if (restResults.isEmpty()) {
            sb.append("No restaurant data available.\n");
        } else {
            for (RestaurantAnalyticsResult r : restResults) {
                sb.append("Restaurant: ").append(r.getName()).append("\n");
                sb.append("  Orders: ").append(r.getTotalOrders()).append("\n");
                sb.append(String.format("  Revenue: ₹%.2f\n", r.getTotalRevenue()));
                sb.append(String.format("  Rating: %.1f\n", r.getAverageRating()));
                sb.append(String.format("  Avg Delivery Time: %.1f mins\n\n", r.getAverageDeliveryTime()));
            }
        }
        
        sb.append("----------------------------------------\n");
        sb.append("CATEGORY PERFORMANCE\n");
        sb.append("----------------------------------------\n");
        
        List<CategoryAnalyticsResult> catResults = analyticsService.getCategoryAnalytics();
        if (catResults.isEmpty()) {
            sb.append("No category data available.\n");
        } else {
            for (CategoryAnalyticsResult c : catResults) {
                sb.append("Category: ").append(c.getCategory()).append("\n");
                sb.append("  Orders: ").append(c.getTotalOrders()).append("\n");
                sb.append(String.format("  Revenue: ₹%.2f\n", c.getTotalRevenue()));
                sb.append(String.format("  Avg Price: ₹%.2f\n\n", c.getAveragePrice()));
            }
        }

        return sb.toString();
    }

    public boolean saveReportToFile(String reportText, String filename) {
        File reportsDir = new File("reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }

        File file = new File(reportsDir, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(reportText);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
            return false;
        }
    }
}
