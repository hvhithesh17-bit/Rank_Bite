package com.rankbite.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("RankBite - Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel("RANKBITE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel subtitleLabel = new JLabel("Food Delivery Analytics & Restaurant Ranking System", SwingConstants.CENTER);
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        
        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

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
