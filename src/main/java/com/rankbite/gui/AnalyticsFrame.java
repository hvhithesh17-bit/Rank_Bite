package com.rankbite.gui;

import javax.swing.*;
import java.awt.*;

public class AnalyticsFrame extends JFrame {
    
    public AnalyticsFrame() {
        setTitle("RankBite - Analytics (Placeholder)");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Analytics Dashboard", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cardsPanel.add(createCard("Total Restaurants", "--"));
        cardsPanel.add(createCard("Total Orders", "--"));
        cardsPanel.add(createCard("Average Rating", "--"));
        cardsPanel.add(createCard("Avg Delivery Time", "--"));
        cardsPanel.add(createCard("Average Price", "--"));
        
        add(cardsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> this.dispose());
        bottomPanel.add(closeBtn);
        
        add(bottomPanel, BorderLayout.SOUTH);
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
