package com.rankbite.gui;

import com.rankbite.service.AnalyticsService;
import com.rankbite.service.ReportService;

import javax.swing.*;
import java.awt.*;

public class ReportsFrame extends JFrame {
    private JTextArea reportArea;
    private ReportService reportService;

    public ReportsFrame() {
        reportService = new ReportService(new AnalyticsService());

        setTitle("RankBite - System Reports");
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
        reportArea.setText("Click 'Generate Report' to load data from MySQL...\n(Ensure DatabaseConnection credentials are correct)");
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
            String reportText = reportService.generateOverallReportText();
            reportArea.setText(reportText);
            reportArea.setCaretPosition(0);
        });
        
        saveBtn.addActionListener(e -> {
            String text = reportArea.getText();
            if (text.isEmpty() || text.startsWith("Click 'Generate Report'")) {
                JOptionPane.showMessageDialog(this, "Please generate a report first.");
                return;
            }
            
            String filename = "overall_report_" + System.currentTimeMillis() + ".txt";
            boolean success = reportService.saveReportToFile(text, filename);
            if (success) {
                JOptionPane.showMessageDialog(this, "Report saved successfully as " + filename + " in 'reports/' directory.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save the report.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
