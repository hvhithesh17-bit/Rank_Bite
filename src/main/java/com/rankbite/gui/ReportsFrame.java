package com.rankbite.gui;

import javax.swing.*;
import java.awt.*;

public class ReportsFrame extends JFrame {
    private JTextArea reportArea;

    public ReportsFrame() {
        setTitle("RankBite - Reports (Placeholder)");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("System Reports", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportArea.setText("Click 'Generate Report' to load data...");
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton generateBtn = new JButton("Generate Report");
        JButton saveBtn = new JButton("Save Report");
        JButton closeBtn = new JButton("Close");
        
        bottomPanel.add(generateBtn);
        bottomPanel.add(saveBtn);
        bottomPanel.add(closeBtn);
        
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Handling
        closeBtn.addActionListener(e -> this.dispose());
        
        generateBtn.addActionListener(e -> {
            reportArea.setText("--- RANKBITE SYSTEM REPORT ---\n\n");
            reportArea.append("This is a placeholder for Nikhil's Reporting Module.\n");
            reportArea.append("Analytics data and SQL-driven insights will appear here.\n");
        });
        
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Report saved successfully! (Simulated)");
        });
    }
}
