package com.flightbookingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Change these as per your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/flight_booking";
    private static final String USER = "root";
    private static final String PASSWORD = "Myangel@063"; // change to your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found.");
        }

        // Return a connection object
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

