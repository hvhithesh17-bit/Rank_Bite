package com.rankbite.gui;

import com.rankbite.model.Restaurant;
import com.rankbite.ranking.RankingResult;
import com.rankbite.ranking.WeightedRankingStrategy;
import com.rankbite.service.RankingService;
import com.rankbite.util.CSVReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RankingFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Restaurant> allRestaurants;
    private RankingService rankingService;
    private WeightedRankingStrategy strategy;

    public RankingFrame() {
        setTitle("RankBite - Restaurant Rankings");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        allRestaurants = CSVReader.readRestaurants("dataset/restaurants.csv");
        rankingService = new RankingService();
        strategy = new WeightedRankingStrategy(); // Using default weights

        // Table setup
        String[] columns = {"Rank", "Restaurant", "Rating Score", "Delivery Score", "Price Score", "Demand Score", "Final Score"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh Ranking");
        JButton viewDetailsBtn = new JButton("View Details");
        JButton closeBtn = new JButton("Close");
        
        bottomPanel.add(refreshBtn);
        bottomPanel.add(viewDetailsBtn);
        bottomPanel.add(closeBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Handling
        refreshBtn.addActionListener(e -> calculateAndLoadRankings());
        closeBtn.addActionListener(e -> this.dispose());
        viewDetailsBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String name = (String) tableModel.getValueAt(row, 1);
                JOptionPane.showMessageDialog(this, "Ranking details for: " + name);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a restaurant first.");
            }
        });

        // Initial Load
        calculateAndLoadRankings();
    }

    private void calculateAndLoadRankings() {
        List<RankingResult> results = rankingService.rankRestaurants(allRestaurants, strategy);
        tableModel.setRowCount(0);
        
        for (RankingResult res : results) {
            tableModel.addRow(new Object[]{
                    res.getRank(),
                    res.getRestaurant().getName(),
                    String.format("%.2f", res.getRatingScore()),
                    String.format("%.2f", res.getDeliveryScore()),
                    String.format("%.2f", res.getPriceScore()),
                    String.format("%.2f", res.getDemandScore()),
                    String.format("%.2f", res.getFinalScore())
            });
        }
    }
}
