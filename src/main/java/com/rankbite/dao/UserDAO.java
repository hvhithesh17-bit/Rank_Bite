package com.rankbite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Authenticates a user against the MySQL database.
     * 
     * @param username The username entered in the GUI.
     * @param password The password entered in the GUI.
     * @return true if credentials are valid, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // We use ? to prevent SQL Injection attacks
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password); // Note: In a real app, you should hash this password!
            
            try (ResultSet rs = stmt.executeQuery()) {
                // If rs.next() is true, it means a matching user was found in the database
                return rs.next();
            }
            
        } catch (SQLException e) {
            System.err.println("Database authentication error: " + e.getMessage());
            return false;
        }
    }
}
