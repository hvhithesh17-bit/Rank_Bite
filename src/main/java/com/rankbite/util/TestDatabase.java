package com.rankbite.util;

import java.sql.Connection;

public class TestDatabase {

    public static void main(String[] args) {

        try {
            Connection connection =
                    DatabaseConnection.getConnection();

            System.out.println("Database connected successfully!");

            connection.close();

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}