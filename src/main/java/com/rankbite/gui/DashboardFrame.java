package com.rankbite.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final String userRole;
    private final String userName;

    public DashboardFrame() {
        this("Admin", "User");
    }

    public DashboardFrame(String userRole, String userName) {
        this.userRole = userRole;
        this.userName = userName;

        setTitle("RankBite - Dashboard (" + userRole + ")");
        setSize(520, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JLabel titleLabel = new JLabel("RANKBITE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel subtitleLabel = new JLabel("Food Delivery Analytics & Restaurant Ranking System", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.DARK_GRAY);

        JLabel userLabel = new JLabel("👤 Logged in as: " + userName + " (" + userRole + ")", SwingConstants.CENTER);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        userLabel.setForeground(new Color(25, 118, 210));

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        headerPanel.add(userLabel);
        
        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 25, 100));

        JButton restaurantsBtn = new JButton("Restaurants");
        JButton rankingBtn = new JButton("Ranking");
        JButton analyticsBtn = new JButton("Analytics");
        JButton reportsBtn = new JButton("Reports");
        JButton logoutBtn = new JButton("Logout");

        buttonPanel.add(restaurantsBtn);
        buttonPanel.add(rankingBtn);
        buttonPanel.add(analyticsBtn);
        buttonPanel.add(reportsBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Event Listeners
        restaurantsBtn.addActionListener(e -> new RestaurantFrame().setVisible(true));
        
        rankingBtn.addActionListener(e -> new RankingFrame().setVisible(true));
        
        analyticsBtn.addActionListener(e -> new AnalyticsFrame().setVisible(true));
        
        reportsBtn.addActionListener(e -> new ReportsFrame().setVisible(true));
        
        logoutBtn.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
