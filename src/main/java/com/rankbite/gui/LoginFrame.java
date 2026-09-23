package com.rankbite.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("RankBite - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        centerPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        centerPanel.add(usernameField);

        centerPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        centerPanel.add(passwordField);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        bottomPanel.add(loginButton);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event Handling
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Temporary hardcoded logic until Dhanush's DB is ready
        if ("admin".equals(username) && "1234".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose(); // Close login frame
            new DashboardFrame().setVisible(true); // Open dashboard
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Try admin/1234", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
