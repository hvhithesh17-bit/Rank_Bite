package com.rankbite.gui;

import com.rankbite.analytics.CategoryAnalyticsResult;
import com.rankbite.analytics.CustomerAnalyticsResult;
import com.rankbite.analytics.RestaurantAnalyticsResult;
import com.rankbite.service.AnalyticsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AnalyticsFrame extends JFrame {
    
    private AnalyticsService analyticsService;

    public AnalyticsFrame() {
        analyticsService = new AnalyticsService();

        setTitle("RankBite - Analytics Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Analytics Dashboard", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Overview Tab
        JPanel overviewPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        overviewPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        overviewPanel.add(createCard("Total Restaurants", String.valueOf(analyticsService.getTotalRestaurants())));
        overviewPanel.add(createCard("Total Orders", String.valueOf(analyticsService.getTotalOrders())));
        overviewPanel.add(createCard("Total Revenue", String.format("₹%.2f", analyticsService.getTotalRevenue())));
        overviewPanel.add(createCard("Avg Delivery Time", String.format("%.1f m", analyticsService.getAverageDeliveryTime())));
        int peakHour = analyticsService.getPeakOrderingHour();
        String peakHourStr = peakHour == -1 ? "N/A" : String.format("%02d:00", peakHour);
        overviewPanel.add(createCard("Peak Hour", peakHourStr));
        tabbedPane.addTab("Overview", overviewPanel);

        // 2. Restaurant Analytics Tab
        tabbedPane.addTab("Restaurants", createRestaurantTablePanel());

        // 3. Category Analytics Tab
        tabbedPane.addTab("Categories", createCategoryTablePanel());

        // 4. Customer Analytics Tab
        tabbedPane.addTab("Customers", createCustomerTablePanel());

        add(tabbedPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> this.dispose());
        bottomPanel.add(closeBtn);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createRestaurantTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"ID", "Name", "Orders", "Revenue", "Avg Rating", "Avg Delivery"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        List<RestaurantAnalyticsResult> results = analyticsService.getRestaurantAnalytics();
        for (RestaurantAnalyticsResult r : results) {
            model.addRow(new Object[]{r.getRestaurantId(), r.getName(), r.getTotalOrders(), String.format("₹%.2f", r.getTotalRevenue()), String.format("%.1f", r.getAverageRating()), String.format("%.1f", r.getAverageDeliveryTime())});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCategoryTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Category", "Orders", "Revenue", "Avg Price"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        List<CategoryAnalyticsResult> results = analyticsService.getCategoryAnalytics();
        for (CategoryAnalyticsResult c : results) {
            model.addRow(new Object[]{c.getCategory(), c.getTotalOrders(), String.format("₹%.2f", c.getTotalRevenue()), String.format("₹%.2f", c.getAveragePrice())});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomerTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Customer ID", "Orders", "Total Revenue", "Avg Order Value"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        List<CustomerAnalyticsResult> results = analyticsService.getCustomerAnalytics();
        for (CustomerAnalyticsResult c : results) {
            model.addRow(new Object[]{c.getCustomerId(), c.getTotalOrders(), String.format("₹%.2f", c.getTotalRevenue()), String.format("₹%.2f", c.getAverageOrderValue())});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCard(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }
}
