package com.houseprice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Utility using JDBC
 * Singleton pattern for database connection management
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/houseprice_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DatabaseConnection() {
    }

    /**
     * Get singleton instance of DatabaseConnection
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Establish connection to database
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIVER);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new SQLException("Failed to establish database connection", e);
        }
    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test database connection
     */
    public boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
