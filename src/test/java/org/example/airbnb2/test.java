package org.example.airbnb2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    private static final String URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private static final String USER = "postgres"; // Replace with your PostgreSQL username
    private static final String PASSWORD = "andrastefi04"; // Replace with your PostgreSQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to PostgreSQL successfully!");
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
}
}
}