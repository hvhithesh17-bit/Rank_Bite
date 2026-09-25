package com.rankbite.gui;

import com.rankbite.dao.CustomerDAO;
import com.rankbite.dao.OwnerDAO;
import com.rankbite.dao.UserDAO;
import com.rankbite.model.Customer;
import com.rankbite.model.Owner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * RankBite Authentication Portal
 * 
 * Provides role-based authentication hierarchy:
 * Customer
 *  ├── Register
 *  └── Login
 * Owner
 *  ├── Register
 *  └── Login
 * Plus Admin / Demo login fallback.
 */
public class LoginFrame extends JFrame {

    private final CustomerDAO customerDAO;
    private final OwnerDAO ownerDAO;
    private final UserDAO userDAO;

    // Customer fields
    private JTextField custLoginEmailField;
    private JPasswordField custLoginPasswordField;
    private JTextField custRegNameField;
    private JTextField custRegEmailField;
    private JPasswordField custRegPasswordField;
    private JTextField custRegPhoneField;
    private CardLayout custCardLayout;
    private JPanel custCardPanel;

    // Owner fields
    private JTextField ownerLoginEmailField;
    private JPasswordField ownerLoginPasswordField;
    private JTextField ownerRegNameField;
    private JTextField ownerRegEmailField;
    private JPasswordField ownerRegPasswordField;
    private JTextField ownerRegPhoneField;
    private CardLayout ownerCardLayout;
    private JPanel ownerCardPanel;

    // Admin demo fields
    private JTextField adminUsernameField;
    private JPasswordField adminPasswordField;

    public LoginFrame() {
        this.customerDAO = new CustomerDAO();
        this.ownerDAO = new OwnerDAO();
        this.userDAO = new UserDAO();

        initUI();
    }

    private void initUI() {
        setTitle("RankBite - Customer & Owner Portal");
        setSize(560, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Header Banner
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Tabbed Pane for Customer, Owner, Admin
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));

        tabbedPane.addTab("👤 Customer", createCustomerPanel());
        tabbedPane.addTab("🏪 Owner", createOwnerPanel());
        tabbedPane.addTab("🔑 Admin / Demo", createAdminPanel());

        add(tabbedPane, BorderLayout.CENTER);

        // Bottom Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBorder(new EmptyBorder(5, 15, 10, 15));
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        exitButton.addActionListener(e -> System.exit(0));
        footerPanel.add(exitButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(24, 32, 47));
        header.setBorder(new EmptyBorder(18, 20, 18, 20));

        JLabel titleLabel = new JLabel("🍽️ RANKBITE");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 152, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel("Food Delivery Analytics & Restaurant Ranking System");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subLabel.setForeground(new Color(200, 210, 225));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel portalLabel = new JLabel("Authentication Portal");
        portalLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        portalLabel.setForeground(Color.WHITE);
        portalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(titleLabel);
        header.add(Box.createRigidArea(new Dimension(0, 4)));
        header.add(subLabel);
        header.add(Box.createRigidArea(new Dimension(0, 6)));
        header.add(portalLabel);

        return header;
    }

    // =========================================================================
    // CUSTOMER SECTION (Login & Register)
    // =========================================================================
    private JPanel createCustomerPanel() {
        custCardLayout = new CardLayout();
        custCardPanel = new JPanel(custCardLayout);

        custCardPanel.add(createCustomerLoginCard(), "CUST_LOGIN");
        custCardPanel.add(createCustomerRegisterCard(), "CUST_REGISTER");

        return custCardPanel;
    }

    private JPanel createCustomerLoginCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Subheader
        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 4));
        JLabel title = new JLabel("Customer Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel subtitle = new JLabel("Sign in to explore ranked restaurants and order metrics", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(Color.GRAY);
        titlePanel.add(title);
        titlePanel.add(subtitle);
        panel.add(titlePanel, BorderLayout.NORTH);

        // Form fields
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        form.add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        custLoginEmailField = new JTextField(20);
        form.add(custLoginEmailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        custLoginPasswordField = new JPasswordField(20);
        form.add(custLoginPasswordField, gbc);

        panel.add(form, BorderLayout.CENTER);

        // Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        JButton loginBtn = new JButton("Login as Customer");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setBackground(new Color(33, 150, 243));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(280, 40));
        loginBtn.addActionListener(e -> handleCustomerLogin());

        JButton switchToRegBtn = new JButton("Don't have an account? Register here");
        switchToRegBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        switchToRegBtn.setBorderPainted(false);
        switchToRegBtn.setContentAreaFilled(false);
        switchToRegBtn.setForeground(new Color(25, 118, 210));
        switchToRegBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchToRegBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToRegBtn.addActionListener(e -> custCardLayout.show(custCardPanel, "CUST_REGISTER"));

        actionPanel.add(loginBtn);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        actionPanel.add(switchToRegBtn);

        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCustomerRegisterCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 30, 15, 30));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 4));
        JLabel title = new JLabel("Customer Registration", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel subtitle = new JLabel("Create a new customer account", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(Color.GRAY);
        titlePanel.add(title);
        titlePanel.add(subtitle);
        panel.add(titlePanel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        form.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        custRegNameField = new JTextField(20);
        form.add(custRegNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        form.add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        custRegEmailField = new JTextField(20);
        form.add(custRegEmailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.7;
        custRegPasswordField = new JPasswordField(20);
        form.add(custRegPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        form.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.7;
        custRegPhoneField = new JTextField(20);
        form.add(custRegPhoneField, gbc);

        panel.add(form, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        JButton registerBtn = new JButton("Register Customer");
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerBtn.setBackground(new Color(46, 125, 50));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(280, 40));
        registerBtn.addActionListener(e -> handleCustomerRegister());

        JButton switchToLoginBtn = new JButton("Already have an account? Sign In");
        switchToLoginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        switchToLoginBtn.setBorderPainted(false);
        switchToLoginBtn.setContentAreaFilled(false);
        switchToLoginBtn.setForeground(new Color(25, 118, 210));
        switchToLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchToLoginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToLoginBtn.addActionListener(e -> custCardLayout.show(custCardPanel, "CUST_LOGIN"));

        actionPanel.add(registerBtn);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        actionPanel.add(switchToLoginBtn);

        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void handleCustomerLogin() {
        String email = custLoginEmailField.getText().trim();
        String password = new String(custLoginPasswordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Email and Password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Customer customer = customerDAO.login(email, password);
            if (customer != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!\nWelcome back, " + customer.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new DashboardFrame("Customer", customer.getName()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleCustomerRegister() {
        String name = custRegNameField.getText().trim();
        String email = custRegEmailField.getText().trim();
        String password = new String(custRegPasswordField.getPassword()).trim();
        String phone = custRegPhoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields (Name, Email, Password, Phone) are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Customer customer = new Customer(name, email, password, phone);
        try {
            boolean success = customerDAO.register(customer);
            if (success) {
                JOptionPane.showMessageDialog(this, "Customer Registered Successfully!\nPlease sign in with your credentials.", "Success", JOptionPane.INFORMATION_MESSAGE);
                custRegNameField.setText("");
                custRegEmailField.setText("");
                custRegPasswordField.setText("");
                custRegPhoneField.setText("");

                custLoginEmailField.setText(email);
                custLoginPasswordField.setText("");
                custCardLayout.show(custCardPanel, "CUST_LOGIN");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Email may already be in use.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // =========================================================================
    // OWNER SECTION (Login & Register)
    // =========================================================================
    private JPanel createOwnerPanel() {
        ownerCardLayout = new CardLayout();
        ownerCardPanel = new JPanel(ownerCardLayout);

        ownerCardPanel.add(createOwnerLoginCard(), "OWNER_LOGIN");
        ownerCardPanel.add(createOwnerRegisterCard(), "OWNER_REGISTER");

        return ownerCardPanel;
    }

    private JPanel createOwnerLoginCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 4));
        JLabel title = new JLabel("Restaurant Owner Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel subtitle = new JLabel("Sign in to manage your restaurant analytics and reports", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(Color.GRAY);
        titlePanel.add(title);
        titlePanel.add(subtitle);
        panel.add(titlePanel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        form.add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        ownerLoginEmailField = new JTextField(20);
        form.add(ownerLoginEmailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        ownerLoginPasswordField = new JPasswordField(20);
        form.add(ownerLoginPasswordField, gbc);

        panel.add(form, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        JButton loginBtn = new JButton("Login as Owner");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setBackground(new Color(230, 81, 0));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(280, 40));
        loginBtn.addActionListener(e -> handleOwnerLogin());

        JButton switchToRegBtn = new JButton("New Owner? Register your Restaurant");
        switchToRegBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        switchToRegBtn.setBorderPainted(false);
        switchToRegBtn.setContentAreaFilled(false);
        switchToRegBtn.setForeground(new Color(230, 81, 0));
        switchToRegBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchToRegBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToRegBtn.addActionListener(e -> ownerCardLayout.show(ownerCardPanel, "OWNER_REGISTER"));

        actionPanel.add(loginBtn);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        actionPanel.add(switchToRegBtn);

        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOwnerRegisterCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 30, 15, 30));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 4));
        JLabel title = new JLabel("Restaurant Owner Registration", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel subtitle = new JLabel("Register your restaurant profile with RankBite", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(Color.GRAY);
        titlePanel.add(title);
        titlePanel.add(subtitle);
        panel.add(titlePanel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        form.add(new JLabel("Owner/Business Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        ownerRegNameField = new JTextField(20);
        form.add(ownerRegNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        form.add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        ownerRegEmailField = new JTextField(20);
        form.add(ownerRegEmailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.7;
        ownerRegPasswordField = new JPasswordField(20);
        form.add(ownerRegPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        form.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.7;
        ownerRegPhoneField = new JTextField(20);
        form.add(ownerRegPhoneField, gbc);

        panel.add(form, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        JButton registerBtn = new JButton("Register Owner");
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerBtn.setBackground(new Color(230, 81, 0));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(280, 40));
        registerBtn.addActionListener(e -> handleOwnerRegister());

        JButton switchToLoginBtn = new JButton("Already registered? Sign In");
        switchToLoginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        switchToLoginBtn.setBorderPainted(false);
        switchToLoginBtn.setContentAreaFilled(false);
        switchToLoginBtn.setForeground(new Color(230, 81, 0));
        switchToLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchToLoginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToLoginBtn.addActionListener(e -> ownerCardLayout.show(ownerCardPanel, "OWNER_LOGIN"));

        actionPanel.add(registerBtn);
        actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        actionPanel.add(switchToLoginBtn);

        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void handleOwnerLogin() {
        String email = ownerLoginEmailField.getText().trim();
        String password = new String(ownerLoginPasswordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Email and Password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Owner owner = ownerDAO.login(email, password);
            if (owner != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!\nWelcome back, " + owner.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new DashboardFrame("Owner", owner.getName()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleOwnerRegister() {
        String name = ownerRegNameField.getText().trim();
        String email = ownerRegEmailField.getText().trim();
        String password = new String(ownerRegPasswordField.getPassword()).trim();
        String phone = ownerRegPhoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields (Name, Email, Password, Phone) are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Owner owner = new Owner(name, email, password, phone);
        try {
            boolean success = ownerDAO.register(owner);
            if (success) {
                JOptionPane.showMessageDialog(this, "Owner Registered Successfully!\nPlease sign in with your credentials.", "Success", JOptionPane.INFORMATION_MESSAGE);
                ownerRegNameField.setText("");
                ownerRegEmailField.setText("");
                ownerRegPasswordField.setText("");
                ownerRegPhoneField.setText("");

                ownerLoginEmailField.setText(email);
                ownerLoginPasswordField.setText("");
                ownerCardLayout.show(ownerCardPanel, "OWNER_LOGIN");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Email may already be in use.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // =========================================================================
    // ADMIN / DEMO FALLBACK SECTION
    // =========================================================================
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 4));
        JLabel title = new JLabel("System Admin / Demo Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel subtitle = new JLabel("Demo Credentials: admin / 1234", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(Color.GRAY);
        titlePanel.add(title);
        titlePanel.add(subtitle);
        panel.add(titlePanel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        form.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        adminUsernameField = new JTextField(20);
        form.add(adminUsernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        adminPasswordField = new JPasswordField(20);
        form.add(adminPasswordField, gbc);

        panel.add(form, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        JButton loginBtn = new JButton("Login as Admin");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setPreferredSize(new Dimension(220, 38));
        loginBtn.addActionListener(e -> handleAdminLogin());
        actionPanel.add(loginBtn);

        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void handleAdminLogin() {
        String username = adminUsernameField.getText().trim();
        String password = new String(adminPasswordField.getPassword()).trim();

        boolean isAuthenticated = false;

        try {
            isAuthenticated = userDAO.authenticate(username, password);
        } catch (Exception ex) {
            System.err.println("DB Auth failed, trying fallback...");
        }

        if (!isAuthenticated && "admin".equals(username) && "1234".equals(password)) {
            isAuthenticated = true;
        }

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(this, "Admin Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new DashboardFrame("Admin", username).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
