package com.rankbite.gui;

import com.rankbite.model.Restaurant;
import com.rankbite.util.CSVReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> categoryCombo;
    private List<Restaurant> allRestaurants;

    public RestaurantFrame() {
        setTitle("RankBite - Restaurants");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        allRestaurants = CSVReader.readRestaurants("dataset/restaurants.csv");

        // Top Panel for Filters
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search Name:"));
        searchField = new JTextField(15);
        topPanel.add(searchField);

        topPanel.add(new JLabel("Category:"));
        String[] categories = {"All", "Indian", "Italian", "Chinese", "Fast Food", "South Indian", "North Indian", "Desserts", "Beverages", "Multi Cuisine"};
        categoryCombo = new JComboBox<>(categories);
        topPanel.add(categoryCombo);

        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");
        topPanel.add(searchBtn);
        topPanel.add(clearBtn);

        add(topPanel, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"ID", "Restaurant", "Category", "Location", "Rating", "Delivery (min)", "Avg Price", "Orders"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        JButton viewDetailsBtn = new JButton("View Details");
        JButton closeBtn = new JButton("Close");
        bottomPanel.add(viewDetailsBtn);
        bottomPanel.add(closeBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Handling
        searchBtn.addActionListener(e -> filterData());
        clearBtn.addActionListener(e -> {
            searchField.setText("");
            categoryCombo.setSelectedIndex(0);
            loadDataToTable(allRestaurants);
        });
        closeBtn.addActionListener(e -> this.dispose());
        viewDetailsBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String name = (String) tableModel.getValueAt(row, 1);
                JOptionPane.showMessageDialog(this, "Details for: " + name + "\n(More details to be implemented)");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a restaurant first.");
            }
        });

        // Initial Load
        loadDataToTable(allRestaurants);
    }

    private void filterData() {
        String query = searchField.getText().trim().toLowerCase();
        String cat = (String) categoryCombo.getSelectedItem();

        List<Restaurant> filtered = new ArrayList<>();
        for (Restaurant r : allRestaurants) {
            boolean nameMatches = query.isEmpty() || r.getName().toLowerCase().contains(query);
            boolean catMatches = "All".equals(cat) || r.getCategory().equalsIgnoreCase(cat);

            if (nameMatches && catMatches) {
                filtered.add(r);
            }
        }
        loadDataToTable(filtered);
    }

    private void loadDataToTable(List<Restaurant> list) {
        tableModel.setRowCount(0);
        for (Restaurant r : list) {
            tableModel.addRow(new Object[]{
                    r.getRestaurantId(),
                    r.getName(),
                    r.getCategory(),
                    r.getLocation(),
                    r.getRating(),
                    r.getAverageDeliveryTime(),
                    r.getAveragePrice(),
                    r.getOrderCount()
            });
        }
    }
}
